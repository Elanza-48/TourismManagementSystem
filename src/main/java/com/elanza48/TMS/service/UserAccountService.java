package com.elanza48.TMS.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityExistsException;

import com.elanza48.TMS.model.dao.UserRoleRepository;
import com.elanza48.TMS.model.dto.UserAccountDTO;
import com.elanza48.TMS.model.entity.UserRole;
import com.elanza48.TMS.model.mapper.DtoToModelMapper;
import com.elanza48.TMS.model.mapper.ModelDtoToMapper;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elanza48.TMS.model.dao.UserAccountRepository;
import com.elanza48.TMS.model.entity.UserAccount;

@Log4j2
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
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

		Optional<UserAccount> userAccount= findUser(email);
		if(userAccount.isEmpty() || !userAccount.get().getEmail().equals(email))
			throw new UsernameNotFoundException(String.format("LOGIN: [status: 'not found' ,user: %s", email));
		return new UserCredentialDetails(findUser(email).get());
	};

	@Transactional
	public UserAccount createUser(UserAccount user, String password) throws EntityExistsException {
		if(userRepo.existsByEmail(user.getEmail())){
			throw new EntityExistsException("User already exists !");
		}
		user.setPassword(encoder.encode(password));
		user.setRole(userRoleRepo.findByName(UserRole.ROLES.USER.name()).get());
		return userRepo.save(user);
	}

	@Transactional
	public Optional<UserAccount> findUser(UUID id) {
		return userRepo.findById(id);
	}

	@Transactional
	public  Optional<UserAccount> findUser(String email) {
		return userRepo.findByEmailEquals(email);
	}
	
	@Transactional
	public Optional<UserAccountDTO> updateUser(UserAccountDTO userAccountDTO, String email){
		Optional<UserAccount> userAccount= findUser(email);
		if(userAccount.isEmpty()) throw new NoSuchElementException();
		UserAccount uAcc= dtoToModelMapper.userAccountDtoToModel(userAccountDTO, userAccount.get());
		log.info(uAcc.toString());
		return Optional.of(modelDtoToMapper.userAccountModelToDto(
				userRepo.save(userAccount.get())
		));
	}

	@Transactional
	public boolean updateUserPassword(String email, String current_pass, String new_pass) throws
		BadCredentialsException{
		Optional<UserAccount> userAccount= findUser(email);
		if(userAccount.isEmpty()) throw new NoSuchElementException();
		if(!encoder.matches(current_pass, userAccount.get().getPassword())){
			throw new BadCredentialsException("Incorrect password !");
		}

		userAccount.get().setPassword(encoder.encode(new_pass));
		UserAccount userAcc=null;
		userAcc=userRepo.save(userAccount.get());
		if(userAcc!=null){
			log.info("password updated [email: "+email+"]");
			return true;
		}
		return false;
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
