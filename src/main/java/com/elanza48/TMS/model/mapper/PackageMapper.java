package com.elanza48.TMS.model.mapper;

import java.util.List;

import com.elanza48.TMS.model.dto.PackageDTO;
import com.elanza48.TMS.model.entity.Package;

import org.mapstruct.Mapper;

@Mapper
public interface PackageMapper {

  PackageDTO packageModelToDto(Package package1);
  List<PackageDTO> packageModelToDtoList(List<Package> packages);
  Package packageDtoToModel(PackageDTO packageDTO);
}
