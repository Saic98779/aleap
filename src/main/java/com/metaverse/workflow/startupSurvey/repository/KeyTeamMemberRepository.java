package com.metaverse.workflow.startupSurvey.repository;

import com.metaverse.workflow.startupSurvey.model.KeyTeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyTeamMemberRepository extends JpaRepository<KeyTeamMember, Long> {

    List<KeyTeamMember> findByStartupSurveyId(Long startupSurveyId);
}

