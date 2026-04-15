package com.metaverse.workflow.aleap_participant.service;

import com.metaverse.workflow.aleap_participant.repository.AleapParticipantRepository;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.AleapParticipant;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AleapParticipantService {

    private final AleapParticipantRepository repository;

    public WorkflowResponse create(AleapParticipantRequest request) {

        AleapParticipant entity = AleapParticipantMapper.mapToEntity(request);
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

        repository.deleteById(id);

        return WorkflowResponse.builder()
                .status(200)
                .message("Participant Deleted Successfully")
                .data(null)
                .build();
    }
    public WorkflowResponse findByMobileNumber(Long mobileNumber) {

        AleapParticipant entity = repository.findByContactNo(mobileNumber)
                .orElseThrow(() -> new RuntimeException("Participant not found with mobile number: " + mobileNumber));

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(AleapParticipantMapper.mapToResponse(entity))
                .build();
    }

    public WorkflowResponse uploadExcel(MultipartFile file) {

        if (file.isEmpty()) {
            return WorkflowResponse.builder()
                    .message("File is empty")
                    .status(400)
                    .build();
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            List<AleapParticipant> participants = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                AleapParticipant p = AleapParticipant.builder()
                        .participantName(getString(row.getCell(0)))
                        .gender(getString(row.getCell(1)))
                        .category(getString(row.getCell(2)))
                        .disability(getString(row.getCell(3)))
                        .dob(getDate(row.getCell(4)))
                        .age(getInteger(row.getCell(5)))
                        .aadharNo(getString(row.getCell(6)))
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
                        .build();

                participants.add(p);
            }

            repository.saveAll(participants);

            return WorkflowResponse.builder()
                    .message("Excel data imported successfully")
                    .status(200)
                    .data(participants.size() + " records inserted")
                    .build();

        } catch (Exception e) {
            return WorkflowResponse.builder()
                    .message("Error processing file: " + e.getMessage())
                    .status(500)
                    .build();
        }
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
