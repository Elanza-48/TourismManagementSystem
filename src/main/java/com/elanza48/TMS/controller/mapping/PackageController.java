package com.elanza48.TMS.controller.mapping;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.entity.Package;
import com.elanza48.TMS.service.PackageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/tourPackages")
public class PackageController {

  @Autowired
  private PackageService packageService;

  @GetMapping
  public List<Package> getAllActivePackages(){
      return packageService.getAllPackages();
  }

  @GetMapping("/{id}")
  public Package getPackageById(@PathVariable UUID id){
      return packageService.getPackageById(id);
  }

  @PostMapping
  public Package insertPackage(@RequestBody Package body) {
      return packageService.createPackage(body);
  }
}
