package com.elanza48.TMS.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.dao.UserRoleRepository;
import com.elanza48.TMS.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elanza48.TMS.model.dao.UserAccountRepository;
import com.elanza48.TMS.model.entity.UserAccount;

@Service
@Transactional
public class UserAccountService implements UserDetailsService{

	UserAccountRepository userRepo;
	UserRoleRepository userRoleRepo;
	PasswordEncoder encoder;

	@Autowired
	public void setUserRepo(UserAccountRepository userRepo) {
		this.userRepo = userRepo;
	}
	@Autowired
	public void setEncoder(PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	@Autowired
	public void setUserRoleRepo(UserRoleRepository userRoleRepo) {
		this.userRoleRepo = userRoleRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

		Optional<UserAccount> userAccount= findUser(email);
		if(userAccount.isEmpty() || !userAccount.get().getEmail().equals(email))
			throw new UsernameNotFoundException("LOGIN: [status: 'not found' ,user: "+email+"]");
		return new UserCredentialDetails(findUser(email).get());
	};

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public UserAccount createUser(UserAccount user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(userRoleRepo.findByName(UserRole.ROLES.USER.name()).get());
		return userRepo.save(user);
	}

	public  Optional<UserAccount> findUser(String email) {
		return userRepo.findByEmailEquals(email);
	}

	public Optional<UserAccount> findUser(UUID id) {
		return userRepo.findById(id);
	}

	public void deleteUser(UUID id) {
		userRepo.deleteById(id);
	}

	public void deleteUser(String email) {
		userRepo.deleteById(findUser(email).get().getId());
	}

	public List<UserAccount> getAllUser() {
		return userRepo.findAll();
	}

}
