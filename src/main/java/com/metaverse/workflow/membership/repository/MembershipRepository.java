package com.metaverse.workflow.membership.repository;

import com.metaverse.workflow.model.MembershipApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipApplication, Long> {

    List<MembershipApplication> findByCreatedBy_UserId(String userId);
}