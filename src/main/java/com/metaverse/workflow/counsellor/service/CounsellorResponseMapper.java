package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.CounsellorRegistration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CounsellorResponseMapper {

    public static CounsellorRegistrationResponse map(CounsellorRegistration counsellorRegistration) {

        CounsellorRegistrationResponse.CounsellorRegistrationResponseBuilder builder =
                CounsellorRegistrationResponse.builder()
                        .counsellorRegistrationId(counsellorRegistration.getCounsellorRegistrationId())
                        .dateOfRegistration(DateUtil.dateToString(
                                counsellorRegistration.getDateOfRegistration(), "dd-MM-yyyy"))
                        .nameOfCounsellor(counsellorRegistration.getNameOfCounsellor())
                        .dateOfBirth(DateUtil.dateToString(
                                counsellorRegistration.getDateOfBirth(), "dd-MM-yyyy"))
                        .gender(counsellorRegistration.getGender())
                        .socialCategory(counsellorRegistration.getSocialCategory())
                        .educationalQualification(counsellorRegistration.getEducationalQualification())
                        .districtId(counsellorRegistration.getRegistrationMandal()
                                .getDistrict().getDistrictId())
                        .districtName(counsellorRegistration.getRegistrationMandal()
                                .getDistrict().getDistrictName())
                        .village(counsellorRegistration.getVillage())
                        .houseNo(counsellorRegistration.getHouseNo())
                        .streetName(counsellorRegistration.getStreetName())
                        .pincode(counsellorRegistration.getPincode())
                        .landmark(counsellorRegistration.getLandmark())
                        .experience(counsellorRegistration.getExperience())
                        .designation(counsellorRegistration.getDesignation())
                        .specialization(counsellorRegistration.getSpecialization())
                        .contactNo(counsellorRegistration.getContactNo())
                        .altContactNo(counsellorRegistration.getAltContactNo())
                        .emailId(counsellorRegistration.getEmailId());
        if (counsellorRegistration.getCounsellorAllotments() != null &&
                !counsellorRegistration.getCounsellorAllotments().isEmpty()) {

            List<CounsellorAllotmentsResponse> allotmentResponses =
                    counsellorRegistration.getCounsellorAllotments()
                            .stream()
                            .map(allotment -> CounsellorAllotmentsResponse.builder()
                                    .counsellorAllotmentId(allotment.getCounsellorAllotmentId())
                                    .counsellorName(allotment.getCounsellorRegistration().getNameOfCounsellor())
                                    .mandalId(allotment.getAllotedMandal() != null ?
                                            allotment.getAllotedMandal().getMandalId() : null)
                                    .mandalName(allotment.getAllotedMandal() != null ?
                                            allotment.getAllotedMandal().getMandalName() : null)
                                    .startDate(allotment.getStartDate() != null ?
                                            DateUtil.dateToString(allotment.getStartDate(), "dd-MM-yyyy") : null)
                                    .endDate(allotment.getEndDate() != null ?
                                            DateUtil.dateToString(allotment.getEndDate(), "dd-MM-yyyy") : null)
                                    .dateOfAllotment(allotment.getDateOfAllotment() != null ?
                                            DateUtil.dateToString(allotment.getDateOfAllotment(), "dd-MM-yyyy") : null)
                                    .build())
                            .toList();

            builder.allotments(allotmentResponses);
        }

        return builder.build();
    }
}