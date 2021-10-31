package com.elanza48.TMS.model.dao;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID>{

	public Optional<UserRole> findById(UUID id);
	public Optional<UserRole> findByName(String name);
  
}
