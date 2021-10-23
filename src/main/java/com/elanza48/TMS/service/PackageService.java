package com.elanza48.TMS.service;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.dao.PackageRepository;
import com.elanza48.TMS.model.dto.Package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PackageService {

  @Autowired
  private PackageRepository packageRepository;

  @Transactional(readOnly = true)
  public List<Package> getAllPackages(){
    return packageRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Package getPackageById(UUID id){
    return packageRepository.findById(id).get();
  }

  @Transactional
  public Package  createPackage(Package entity){
    return packageRepository.save(entity);
  }
  
}
