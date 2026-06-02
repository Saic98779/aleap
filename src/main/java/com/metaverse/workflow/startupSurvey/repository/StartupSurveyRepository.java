package com.metaverse.workflow.startupSurvey.repository;

import com.metaverse.workflow.startupSurvey.model.StartupSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StartupSurveyRepository extends JpaRepository<StartupSurvey, Long> {

    Optional<StartupSurvey> findByEmail(String email);

    Optional<StartupSurvey> findByStartupName(String startupName);
}

