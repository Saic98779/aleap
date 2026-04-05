package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.DataException;

public interface CounsellorService {

    WorkflowResponse saveCounsellor(CounsellorRegistrationRequest counsellorRequest);

    WorkflowResponse getCounsellorByMandal(Integer mandalId);

    WorkflowResponse getAllCounsellors();

    WorkflowResponse deleteCounsellor(Long id);

    WorkflowResponse getCounsellorById(Long id);

    WorkflowResponse assignMandal(CounsellorAllotmentsRequest request) throws DataException;

    WorkflowResponse updateCounsellor(Long id, CounsellorRegistrationRequest request);

    WorkflowResponse getAllAllotments();

    WorkflowResponse getByContact(Long contactNo) throws DataException;

}
