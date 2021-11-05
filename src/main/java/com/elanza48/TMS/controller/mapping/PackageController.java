package com.elanza48.TMS.controller.mapping;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.ModelDtoMapper;
import com.elanza48.TMS.model.dto.PackageDTO;
import com.elanza48.TMS.model.entity.Package;
import com.elanza48.TMS.service.PackageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/tourPackages")
public class PackageController {

  private PackageService packageService;
  private ModelDtoMapper modelDtoMapper;

    @Autowired
    public void setModelDtoMapper(ModelDtoMapper modelDtoMapper) {
        this.modelDtoMapper = modelDtoMapper;
    }

    @Autowired
  public void setPackageService(PackageService packageService) {
      this.packageService = packageService;
  }

  @GetMapping
  public List<PackageDTO> getAllActivePackages(){
      return modelDtoMapper.packageModelToDtoList(packageService.getAllPackages());
  }

  @GetMapping("/{id}")
  public PackageDTO getPackageById(@PathVariable UUID id){
      return modelDtoMapper.packageModelToDto(packageService.getPackageById(id));
  }

  @PostMapping
  @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
  public Package insertPackage(@RequestBody Package body) {
      return packageService.createPackage(body);
  }
}
