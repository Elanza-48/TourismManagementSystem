package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.dto.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
  Optional<Booking> findById(UUID id);
}
