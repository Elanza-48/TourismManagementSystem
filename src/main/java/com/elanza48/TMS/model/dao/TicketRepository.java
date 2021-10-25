package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
  Optional<Ticket> findById(UUID id);
}
