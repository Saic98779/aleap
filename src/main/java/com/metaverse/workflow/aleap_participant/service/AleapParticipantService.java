package com.metaverse.workflow.aleap_participant.service;

import com.metaverse.workflow.aleap_participant.repository.AleapParticipantRepository;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.event.repository.EventDetailsRepository;
import com.metaverse.workflow.model.AleapParticipant;
import com.metaverse.workflow.model.EventDetails;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AleapParticipantService {

    private final AleapParticipantRepository repository;
    private final EventDetailsRepository eventRepository;

    public WorkflowResponse create(AleapParticipantRequest request) {

        String aadharNo = request.getAadharNo() != null ? request.getAadharNo().trim() : null;

        // Aadhaar validation
        if (aadharNo == null || !aadharNo.matches("\\d{12}")) {
            return WorkflowResponse.builder()
                    .status(400)
                    .message("Invalid Aadhaar number. It must be exactly 12 digits")
                    .build();
        }

        List<EventDetails> events = new ArrayList<>();
        if (request.getEventIds() != null && !request.getEventIds().isEmpty()) {
            events = eventRepository.findAllById(request.getEventIds());

            if (events.isEmpty()) {
                return WorkflowResponse.builder()
                        .status(400)
                        .message("Invalid eventIds provided")
                        .build();
            }
        }

        Optional<AleapParticipant> existingOpt = repository.findByAadharNo(aadharNo);

        //  Participant already exists
        if (existingOpt.isPresent()) {

            AleapParticipant existing = existingOpt.get();

            Set<EventDetails> currentEvents = new HashSet<>(
                    existing.getEvents() != null ? existing.getEvents() : new ArrayList<>()
            );

            int beforeSize = currentEvents.size();

            currentEvents.addAll(events);

            // Already mapped
            if (currentEvents.size() == beforeSize) {
                return WorkflowResponse.builder()
                        .status(200)
                        .message("Participant already exists and already mapped to given events")
                        .data(AleapParticipantMapper.mapToResponse(existing))
                        .build();
            }

            existing.setEvents(new ArrayList<>(currentEvents));

            AleapParticipant updated = repository.save(existing);

            return WorkflowResponse.builder()
                    .status(200)
                    .message("Existing participant found. Events updated successfully")
                    .data(AleapParticipantMapper.mapToResponse(updated))
                    .build();
        }

        // New participant
        AleapParticipant entity = AleapParticipantMapper.mapToEntity(request);
        entity.setAadharNo(aadharNo);
        entity.setEvents(events);

        AleapParticipant saved = repository.save(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Participant Created Successfully")
                .data(AleapParticipantMapper.mapToResponse(saved))
                .build();
    }

    public WorkflowResponse update(Long id, AleapParticipantRequest request) {

        AleapParticipant entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        AleapParticipantMapper.updateEntity(entity, request);
        if (request.getEventIds() != null) {
            List<EventDetails> events = eventRepository.findAllById(request.getEventIds());
            entity.setEvents(events);
        }

        AleapParticipant updated = repository.save(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Participant Updated Successfully")
                .data(AleapParticipantMapper.mapToResponse(updated))
                .build();
    }

    public WorkflowResponse getById(Long id) {

        AleapParticipant entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(AleapParticipantMapper.mapToResponse(entity))
                .build();
    }

    public WorkflowResponse getAll() {

        List<AleapParticipantResponse> list = repository.findAll()
                .stream()
                .map(AleapParticipantMapper::mapToResponse)
                .toList();

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }

    public WorkflowResponse delete(Long id) {

        AleapParticipant entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        if (entity.getEvents() != null) {
            entity.getEvents().forEach(event -> event.getParticipants().remove(entity));
            entity.getEvents().clear();
        }

        repository.delete(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Participant Deleted Successfully")
                .build();
    }
    public WorkflowResponse findByAadharNumber(String aadharNo) {

        AleapParticipant entity = repository.findByAadharNo(aadharNo)
                .orElseThrow(() -> new RuntimeException("Participant not found with Aadhaar number: " + aadharNo));

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(AleapParticipantMapper.mapToResponse(entity))
                .build();
    }

    public WorkflowResponse uploadExcel(MultipartFile file, List<Long> eventIds) {

        if (file.isEmpty()) {
            return WorkflowResponse.builder()
                    .message("File is empty")
                    .status(400)
                    .build();
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);

            List<AleapParticipant> toSave = new ArrayList<>();

            List<String> duplicateAadhars = new ArrayList<>();
            List<String> invalidAadhars = new ArrayList<>();
            List<String> alreadyMapped = new ArrayList<>();

            int insertedCount = 0;
            int updatedCount = 0;

            //  Fetch events once
            List<EventDetails> events = (eventIds != null && !eventIds.isEmpty())
                    ? eventRepository.findAllById(eventIds)
                    : new ArrayList<>();

            if (eventIds != null && !eventIds.isEmpty() && events.isEmpty()) {
                return WorkflowResponse.builder()
                        .status(400)
                        .message("Invalid eventIds provided")
                        .build();
            }

            // Cache existing participants
            Map<String, AleapParticipant> existingMap = repository.findAll()
                    .stream()
                    .filter(p -> p.getAadharNo() != null)
                    .collect(Collectors.toMap(
                            AleapParticipant::getAadharNo,
                            p -> p
                    ));

            Set<String> excelAadharSet = new HashSet<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                String aadharNo = getString(row.getCell(6));

                if (aadharNo == null || aadharNo.trim().isEmpty()) continue;

                aadharNo = aadharNo.trim();

                // Invalid Aadhaar
                if (!isValidAadhar(aadharNo)) {
                    invalidAadhars.add("Row " + (i + 1) + " → " + aadharNo);
                    continue;
                }

                //  Duplicate in Excel
                if (excelAadharSet.contains(aadharNo)) {
                    duplicateAadhars.add(aadharNo);
                    continue;
                }
                excelAadharSet.add(aadharNo);

                AleapParticipant existing = existingMap.get(aadharNo);

                if (existing != null) {

                    Set<EventDetails> currentEvents = new HashSet<>(
                            existing.getEvents() != null ? existing.getEvents() : new ArrayList<>()
                    );

                    int beforeSize = currentEvents.size();

                    currentEvents.addAll(events);

                    if (currentEvents.size() == beforeSize) {
                        alreadyMapped.add(aadharNo);
                        continue;
                    }

                    existing.setEvents(new ArrayList<>(currentEvents));
                    toSave.add(existing);
                    updatedCount++;

                } else {

                    AleapParticipant p = AleapParticipant.builder()
                            .participantName(getString(row.getCell(0)))
                            .gender(getString(row.getCell(1)))
                            .category(getString(row.getCell(2)))
                            .disability(getString(row.getCell(3)))
                            .dob(getDate(row.getCell(4)))
                            .age(getInteger(row.getCell(5)))
                            .aadharNo(aadharNo)
                            .contactNo(getLong(row.getCell(7)))
                            .email(getString(row.getCell(8)))
                            .organizationName(getString(row.getCell(9)))
                            .designation(getString(row.getCell(10)))
                            .state(getString(row.getCell(11)))
                            .district(getString(row.getCell(12)))
                            .mandal(getString(row.getCell(13)))
                            .village(getString(row.getCell(14)))
                            .streetOrBlock(getString(row.getCell(15)))
                            .houseNoOrDoorNo(getString(row.getCell(16)))
                            .pinCode(getString(row.getCell(17)))
                            .otherInformation(getString(row.getCell(18)))
                            .events(events)
                            .build();

                    toSave.add(p);
                    insertedCount++;
                }
            }

            repository.saveAll(toSave);

            return WorkflowResponse.builder()
                    .status(200)
                    .message("Excel processed successfully")
                    .data(Map.of(
                            "insertedCount", insertedCount,
                            "updatedCount", updatedCount,
                            "duplicateCount", duplicateAadhars.size(),
                            "duplicateAadharNumbers", duplicateAadhars,
                            "invalidAadharCount", invalidAadhars.size(),
                            "invalidAadharRows", invalidAadhars,
                            "alreadyMappedCount", alreadyMapped.size(),
                            "alreadyMappedAadhars", alreadyMapped
                    ))
                    .build();

        } catch (Exception e) {
            return WorkflowResponse.builder()
                    .message("Error processing file: " + e.getMessage())
                    .status(500)
                    .build();
        }
    }

    private boolean isValidAadhar(String aadhar) {
        return aadhar != null && aadhar.matches("\\d{12}");
    }
    private String getString(Cell cell) {
        return cell == null ? null : cell.toString().trim();
    }

    private Integer getInteger(Cell cell) {
        try {
            return cell == null ? null : (int) cell.getNumericCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    private Long getLong(Cell cell) {
        try {
            if (cell == null) return null;

            switch (cell.getCellType()) {
                case NUMERIC:
                    return (long) cell.getNumericCellValue();

                case STRING:
                    String str = cell.getStringCellValue().trim();
                    return str.isEmpty() ? null : Long.parseLong(str);

                case FORMULA:
                    return (long) cell.getNumericCellValue();

                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private Date getDate(Cell cell) {
        try {
            return cell == null ? null : cell.getDateCellValue();
        } catch (Exception e) {
            return null;
        }
    }
}
