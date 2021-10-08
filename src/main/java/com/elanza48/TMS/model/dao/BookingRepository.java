package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elanza48.TMS.model.dto.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
