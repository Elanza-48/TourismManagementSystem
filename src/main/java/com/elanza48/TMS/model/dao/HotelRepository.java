package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elanza48.TMS.model.dto.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}