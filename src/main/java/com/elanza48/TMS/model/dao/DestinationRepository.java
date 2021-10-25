package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, UUID> {
  Optional<Destination> findById(UUID id);
}
