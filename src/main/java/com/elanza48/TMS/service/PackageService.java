package com.elanza48.TMS.service;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.dao.PackageRepository;
import com.elanza48.TMS.model.entity.Package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PackageService {

  private PackageRepository packageRepository;
  @Autowired
  public void setPackageRepository(PackageRepository packageRepository) {
    this.packageRepository = packageRepository;
  }

  public List<Package> getAllPackages(){
    return packageRepository.findAll();
  }

  public Package getPackageById(UUID id){
    return packageRepository.findById(id).get();
  }

  public Package  createPackage(Package entity){
    return packageRepository.save(entity);
  }
  
}
