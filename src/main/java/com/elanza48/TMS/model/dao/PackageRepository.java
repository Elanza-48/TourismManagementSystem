package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.dto.Package;

public interface PackageRepository extends JpaRepository<Package, UUID> {
  public Optional<Package> findById(UUID id);

}
