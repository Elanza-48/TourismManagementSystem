package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
  Optional<Payment> findById(UUID id);
}
