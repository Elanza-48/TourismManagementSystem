package com.elanza48.TMS.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elanza48.TMS.model.dao.UserAccountRepository;
import com.elanza48.TMS.model.dto.UserAccount;

@Service
@Transactional
public class UserAccountService implements UserDetailsService{
	
	@Autowired
	UserAccountRepository userRepo;

	@Autowired 
	PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

		Optional<UserAccount> userAccount= findUser(email);
		if(!userAccount.isPresent() || !userAccount.get().getEmail().equals(email))
			throw new UsernameNotFoundException(email+ " not found !");
		return new UserCredentialDetails(findUser(email).get());
	};

	public UserAccount createUser(UserAccount user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	@Transactional(readOnly = true)
	public  Optional<UserAccount> findUser(String email) {
		return userRepo.findByEmailEquals(email);
	}
	
	@Transactional(readOnly = true)
	public Optional<UserAccount> findUser(UUID id) {
		return userRepo.findById(id);
	}

	public UserAccount updateUserById(UUID id, Map<String, Object> userFields){
		Optional<UserAccount> userAccount = findUser(id);

		if(userAccount.isPresent()){
			userFields.forEach((key, value) ->{

				switch(key) {
					case "name":
							userAccount.get().setName((String)value);
							break;
					case "email":
							userAccount.get().setEmail((String)value);
							break;
					case "mobileNo":
							userAccount.get().setMobileNo((long) value);
							break;
					case "role":
							userAccount.get().setRole(UserAccount.UserRole.valueOf((String) value));
							break;
					case "status":
							userAccount.get().setStatus(UserAccount.UserAccountStatus.valueOf((String) value));
							break;
					default:
							break;
				}
			});
		}
		userRepo.save(userAccount.get());
		return userAccount.get();
	}

	public UserAccount updateUserByEmail(String email, Map<String, Object> userFields){
		 return updateUserById(findUser(email).get().getId(), userFields);
	}

	public void deleteUser(UUID id) {
		userRepo.deleteById(id);
	}
	
	public void deleteUser(String email) {
		userRepo.deleteById(findUser(email).get().getId());
	}
	
	@Transactional(readOnly = true)
	public List<UserAccount> getAllUser() {
		return userRepo.findAll();
	}

}
