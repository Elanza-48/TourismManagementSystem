package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.dto.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

	public Optional<UserAccount> findById(UUID id);
	public Optional<UserAccount> findByEmailEquals(String email);
}
