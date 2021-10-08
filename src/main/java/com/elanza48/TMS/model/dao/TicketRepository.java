package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elanza48.TMS.model.dto.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
