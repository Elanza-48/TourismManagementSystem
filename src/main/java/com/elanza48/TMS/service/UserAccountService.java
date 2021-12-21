package com.elanza48.TMS.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import com.elanza48.TMS.model.dao.UserRoleRepository;
import com.elanza48.TMS.model.dto.UserAccountDTO;
import com.elanza48.TMS.model.entity.UserRole;
import com.elanza48.TMS.model.mapper.DtoToModelMapper;
import com.elanza48.TMS.model.mapper.ModelDtoToMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elanza48.TMS.model.dao.UserAccountRepository;
import com.elanza48.TMS.model.entity.UserAccount;

@Slf4j
@Service
public class UserAccountService implements UserDetailsService{

	private UserAccountRepository userRepo;
	private UserRoleRepository userRoleRepo;
	private PasswordEncoder encoder;
	private DtoToModelMapper dtoToModelMapper;
	private ModelDtoToMapper modelDtoToMapper;

	@Autowired
	public void setUserRepo(UserAccountRepository userRepo) {
		this.userRepo = userRepo;
	}
	@Autowired
	public void setEncoder(@Lazy PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	@Autowired
	public void setUserRoleRepo(UserRoleRepository userRoleRepo) {
		this.userRoleRepo = userRoleRepo;
	}
	@Autowired
	public void setDtoToModelMapper(DtoToModelMapper dtoToModelMapper) {
		this.dtoToModelMapper = dtoToModelMapper;
	}
	@Autowired
	public void setModelDtoToMapper(ModelDtoToMapper modelDtoToMapper) {
		this.modelDtoToMapper = modelDtoToMapper;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

		Optional<UserAccount> userAccount= findUser(email);
		if(userAccount.isEmpty() || !userAccount.get().getEmail().equals(email))
			throw new UsernameNotFoundException(String.format("LOGIN: [status: 'not found' ,user: %s", email));
		return new UserCredentialDetails(findUser(email).get());
	};

	@Transactional
	public UserAccount createUser(UserAccount user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(userRoleRepo.findByName(UserRole.ROLES.USER.name()).get());
		return userRepo.save(user);
	}


	@Transactional
	public  Optional<UserAccount> findUser(String email) {
		return userRepo.findByEmailEquals(email);

	}


	public Optional<UserAccountDTO> updateUser(UserAccountDTO userAccountDTO, String email){

		Optional<UserAccount> userAccount= findUser(email);
		if(userAccount.isEmpty()) throw new NoSuchElementException();
		userAccount.get().setName(String.format("Modified %s", userAccount.get().getName()));
		dtoToModelMapper.userAccountDtoToModel(userAccountDTO, userAccount.get());
		return Optional.of(modelDtoToMapper.userAccountModelToDto(
				userRepo.save(userAccount.get())
		));
	}

	@Transactional
	public Optional<UserAccount> findUser(UUID id) {
		return userRepo.findById(id);
	}

	@Transactional
	public void deleteUser(UUID id) {
		userRepo.deleteById(id);
	}

	@Transactional
	public void deleteUser(String email) {
		userRepo.deleteById(findUser(email).get().getId());
	}

	public List<UserAccount> getAllUser() {
		return userRepo.findAll();
	}

}
