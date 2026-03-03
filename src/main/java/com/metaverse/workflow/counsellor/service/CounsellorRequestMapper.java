package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.CounsellorRegistration;
import org.springframework.stereotype.Component;

@Component
public class CounsellorRequestMapper {
    public static CounsellorRegistration map(CounsellorRegistrationRequest counsellorRequest) {
        return CounsellorRegistration.builder()
                .dateOfRegistration(DateUtil.stringToDate(counsellorRequest.getDateOfRegistration(), "dd-MM-yyyy"))
                .nameOfCounsellor(counsellorRequest.getNameOfCounsellor())
                .dateOfBirth(DateUtil.stringToDate(counsellorRequest.getDateOfBirth(), "dd-MM-yyyy"))
                .gender(counsellorRequest.getGender())
                .socialCategory((counsellorRequest.getSocialCategory()))
                .educationalQualification(counsellorRequest.getEducationalQualification())
                .village(counsellorRequest.getVillage())
                .houseNo(counsellorRequest.getHouseNo())
                .streetName(counsellorRequest.getStreetName())
                .pincode(counsellorRequest.getPincode())
                .landmark(counsellorRequest.getLandmark())
                .experience(counsellorRequest.getExpriance())
                .designation(counsellorRequest.getDesignation())
                .specialization(counsellorRequest.getSpecialzation())
                .contactNo(counsellorRequest.getContactNo())
                .altContactNo(counsellorRequest.getAltContactNo())
                .emailId(counsellorRequest.getEmailId())
                .dateOfHiring(DateUtil.stringToDate(counsellorRequest.getDateOfSelection(), "dd-MM-yyyy"))
               .build();
    }



}
