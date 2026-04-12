package com.metaverse.workflow.enquiry.repository;

import com.metaverse.workflow.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}