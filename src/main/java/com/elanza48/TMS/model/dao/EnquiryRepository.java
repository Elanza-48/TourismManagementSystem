package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, UUID> {
  Optional<Enquiry> findById(UUID id);
}
