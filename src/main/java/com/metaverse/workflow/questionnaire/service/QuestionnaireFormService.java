package com.metaverse.workflow.questionnaire.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.model.QuestionnaireForm;
import com.metaverse.workflow.questionnaire.repository.QuestionnaireFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  QuestionnaireFormService {

    private final QuestionnaireFormRepository repository;


    public WorkflowResponse create(QuestionnaireFormRequest request) {
        QuestionnaireForm entity = QuestionnaireFormMapper.mapToQuestionnaireForm(request);
        QuestionnaireFormResponse response = QuestionnaireFormMapper
                .mapToQuestionnaireResponse(repository.save(entity));
        return WorkflowResponse.builder()
                .message("success")
                .data(response)
                .status(200)
                .build();
    }


    public WorkflowResponse update(Long id, QuestionnaireFormRequest request) throws DataException {

        QuestionnaireForm existing = repository.findById(id)
                        .orElseThrow(() -> new DataException(
                                "Questionnaire not found with id " + id,
                                "NOT_FOUND",
                                404
                        ));

        QuestionnaireForm updated = QuestionnaireFormMapper.mapToQuestionnaireForm(request);

        QuestionnaireFormResponse response = QuestionnaireFormMapper.mapToQuestionnaireResponse(repository.save(updated));
        return WorkflowResponse.builder()
                .message("Questionnaire updated")
                .data(response)
                .status(200)
                .build();

    }


    public WorkflowResponse getById(Long id) throws DataException {QuestionnaireForm entity =
                repository.findById(id)
                        .orElseThrow(() -> new DataException(
                                "Questionnaire not found with id " + id,
                                "NOT_FOUND",
                                404
                        ));
        QuestionnaireFormResponse response = QuestionnaireFormMapper.mapToQuestionnaireResponse(entity);
        return WorkflowResponse.builder()
                .message("success")
                .data(response)
                .status(200)
                .build();
    }


    public WorkflowResponse getAll() {

        List<QuestionnaireFormResponse> response = repository.findAll()
                .stream()
                .map(QuestionnaireFormMapper::mapToQuestionnaireResponse)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .message("success")
                .data(response)
                .status(200)
                .build();
    }


    public WorkflowResponse delete(Long id) throws DataException {
        if (!repository.existsById(id)) {
            throw new DataException(
                    "Questionnaire not found with id " + id,
                    "NOT_FOUND",
                    404
            );
        }
        repository.deleteById(id);
        return WorkflowResponse.builder()
                .message("Questionnaire Deleted")
                .status(200)
                .build();
    }
}