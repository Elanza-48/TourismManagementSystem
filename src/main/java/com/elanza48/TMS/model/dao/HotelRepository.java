package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.dto.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
  public Optional<Hotel> findById(UUID id);
}
