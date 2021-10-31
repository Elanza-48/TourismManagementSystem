package com.elanza48.TMS.model.dao;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.UserPrivilege;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege, UUID> {
  

	public Optional<UserPrivilege> findById(UUID id);
	public Optional<UserPrivilege> findByName(String name);
}
