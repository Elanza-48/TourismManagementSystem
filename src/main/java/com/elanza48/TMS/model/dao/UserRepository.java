package com.elanza48.TMS.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elanza48.TMS.model.dto.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

	public UserAccount findByEmailEquals(String email);
}
