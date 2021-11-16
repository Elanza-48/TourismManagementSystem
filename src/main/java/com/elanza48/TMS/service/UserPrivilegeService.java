package com.elanza48.TMS.service;

import com.elanza48.TMS.model.dao.UserPrivilegeRepository;
import com.elanza48.TMS.model.entity.UserPrivilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserPrivilegeService {

    private UserPrivilegeRepository userPrivilegeRepository;

    @Autowired
    public void setUserPrivilegeRepository(UserPrivilegeRepository userPrivilegeRepository) {
        this.userPrivilegeRepository = userPrivilegeRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserPrivilege createPrivilege(UserPrivilege privilege){
        return userPrivilegeRepository.save(privilege);
    }

    public Optional<UserPrivilege> findPrivilege(UUID id){
        return userPrivilegeRepository.findById(id);
    }

    public Optional<UserPrivilege> findPrivilege(String name){
        return userPrivilegeRepository.findByName(name);
    }

    public void deletePrivilege(UUID id){
        userPrivilegeRepository.deleteById(id);
    }

    public void deletePrivilege(String name){
        userPrivilegeRepository.deleteById(findPrivilege(name).get().getId());
    }

    public List<UserPrivilege> getAllPrivilege(){
        return userPrivilegeRepository.findAll();
    }
}
