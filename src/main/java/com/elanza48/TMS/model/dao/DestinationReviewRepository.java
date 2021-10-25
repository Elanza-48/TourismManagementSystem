package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.DestinationReview;

public interface DestinationReviewRepository extends JpaRepository<DestinationReview, UUID> {
  Optional<DestinationReview> findById(UUID id);
}
