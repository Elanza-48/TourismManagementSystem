package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elanza48.TMS.model.dto.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
