package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.Transport;

public interface TransportRepository extends JpaRepository<Transport, UUID> {
  Optional<Transport> findById(UUID id);
}
