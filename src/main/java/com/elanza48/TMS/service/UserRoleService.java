package com.elanza48.TMS.service;

import com.elanza48.TMS.model.dao.UserRoleRepository;
import com.elanza48.TMS.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserRole createRole(UserRole role){
        return userRoleRepository.save(role);
    }

    public Optional<UserRole> findRole(UUID id){
        return userRoleRepository.findById(id);
    }

    public Optional<UserRole> findRole(String name){
        return userRoleRepository.findByName(name);
    }

    public void deleteRole(UUID id){
        userRoleRepository.deleteById(id);
    }

    public void deleteRole(String name){
        userRoleRepository.deleteById(findRole(name).get().getId());
    }

    public List<UserRole> getAllRole(){
        return userRoleRepository.findAll();
    }

}
