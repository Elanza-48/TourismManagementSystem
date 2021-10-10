package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import com.elanza48.TMS.model.dto.HotelReview;

public interface HotelReviewRepository extends JpaRepository<HotelReview, UUID> {

}
