package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.entity.UserAccount;

import javax.persistence.QueryHint;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	public Optional<UserAccount> findById(UUID id);
	public Optional<UserAccount> findByEmailEquals(String email);
}
