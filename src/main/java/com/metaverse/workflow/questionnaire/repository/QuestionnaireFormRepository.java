package com.metaverse.workflow.questionnaire.repository;

import com.metaverse.workflow.model.QuestionnaireForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireFormRepository extends JpaRepository<QuestionnaireForm, Long> {
}