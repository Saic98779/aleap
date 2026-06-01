package com.metaverse.workflow.membership.service;

import com.metaverse.workflow.model.MembershipApplication;
import com.metaverse.workflow.model.User;


public class MembershipApplicationMapper {

    public static MembershipApplication toEntity(MembershipRequest request, User createdBy) {

        return MembershipApplication.builder()
                .name(request.getName())
                .membershipType(request.getMembershipType())
                .applicationDate(request.getApplicationDate())
                .organizationName(request.getOrganizationName())
                .representativeName(request.getRepresentativeName())
                .officeAddress(request.getOfficeAddress())
                .residentialAddress(request.getResidentialAddress())
                .officePhone(request.getOfficePhone())
                .residencePhone(request.getResidencePhone())
                .email(request.getEmail())
                .proposedByName(request.getProposedByName())
                .signaturePath(request.getSignaturePath())
                .secondedByName(request.getSecondedByName())
                .institutionsInvolved(request.getInstitutionsInvolved())
                .institutionsNameAndAddress(request.getInstitutionsNameAndAddress())
                .objectivesActivities(request.getObjectivesActivities())
                .natureOfInvolvement(request.getNatureOfInvolvement())
                .agreedToRules(request.getAgreedToRules())
                .createdBy(createdBy)
                .idProofPath(request.getIdProofPath())
                .photoPath(request.getPhotoPath())
                .amount(request.getAmount())
                .billNo(request.getBillNo())
                .billDate(request.getBillDate())
                .payeeName(request.getPayeeName())
                .paymentType(request.getPaymentType())
                .bankName(request.getBankName())
                .ifscCode(request.getIfscCode())
                .transactionId(request.getTransactionId())
                .checkNo(request.getCheckNo())
                .checkDate(request.getCheckDate())
                .purpose(request.getPurpose())
                .billPath(request.getBillPath())
                .build();
    }

    public static void updateEntity(MembershipApplication entity, MembershipRequest request) {

        entity.setName(request.getName());
        entity.setMembershipType(request.getMembershipType());
        entity.setApplicationDate(request.getApplicationDate());
        entity.setOrganizationName(request.getOrganizationName());
        entity.setRepresentativeName(request.getRepresentativeName());
        entity.setOfficeAddress(request.getOfficeAddress());
        entity.setResidentialAddress(request.getResidentialAddress());
        entity.setOfficePhone(request.getOfficePhone());
        entity.setResidencePhone(request.getResidencePhone());
        entity.setEmail(request.getEmail());

        entity.setProposedByName(request.getProposedByName());
        entity.setSignaturePath(request.getSignaturePath());
        entity.setSecondedByName(request.getSecondedByName());
        entity.setInstitutionsInvolved(request.getInstitutionsInvolved());
        entity.setInstitutionsNameAndAddress(request.getInstitutionsNameAndAddress());
        entity.setObjectivesActivities(request.getObjectivesActivities());
        entity.setNatureOfInvolvement(request.getNatureOfInvolvement());
        entity.setAgreedToRules(request.getAgreedToRules());
        entity.setIdProofPath(request.getIdProofPath());
        entity.setPhotoPath(request.getPhotoPath());

        entity.setAmount(request.getAmount());
        entity.setBillNo(request.getBillNo());
        entity.setBillDate(request.getBillDate());
        entity.setPayeeName(request.getPayeeName());
        entity.setPaymentType(request.getPaymentType());
        entity.setBankName(request.getBankName());
        entity.setIfscCode(request.getIfscCode());
        entity.setTransactionId(request.getTransactionId());
        entity.setCheckNo(request.getCheckNo());
        entity.setCheckDate(request.getCheckDate());
        entity.setPurpose(request.getPurpose());
        entity.setBillPath(request.getBillPath());
    }
}