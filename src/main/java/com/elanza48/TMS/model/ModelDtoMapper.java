package com.elanza48.TMS.model;

import java.util.List;

import com.elanza48.TMS.model.dto.*;
import com.elanza48.TMS.model.entity.*;

import com.elanza48.TMS.model.entity.Package;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ModelDtoMapper {

  @Mapping(target = "password", ignore = true)
  UserAccountDTO userAccountModelToDto(UserAccount userAccount);
  List<UserAccountDTO> userAccountModelToDtoList(List<UserAccount> users);
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "id", ignore = true)
  UserAccount userAccountDtoToModel(UserAccountDTO userAccountDTO);

  
  HotelDTO hotelModelToDto(Hotel hotel);
  List<HotelDTO> hotelModelToDtoList(List<Hotel> hotels);
  @Mapping(target = "id", ignore = true)
  Hotel hotelDtoToModel(HotelDTO hotelDto);


  DestinationDTO destinationModelToDto(Destination dest);
  List<DestinationDTO> destinationModelToDtoList(List<Destination> dests);
  @Mapping(target = "id", ignore = true)
  Destination destinationDtoToModel(DestinationDTO destDto);


  PackageDTO packageModelToDto(Package tourPackage);
  List<PackageDTO> packageModelToDtoList(List<Package> packages);
  @Mapping(target = "id", ignore = true)
  Package packageDtoToModel(PackageDTO packageDTO);


  BookingDTO bookingModelToDto(Booking booking);
  List<BookingDTO> bookingModelToDtoList(List<Booking> bookings);
  @Mapping(target = "id", ignore = true)
  Booking bookingDtoToModel(BookingDTO bookingDTO);


  UserRoleDTO userRoleModelToDto(UserRole userRole);
  List<UserRoleDTO> userRoleModelToDtoList(List<UserRole> userRoles);
  @Mapping(target = "id", ignore = true)
  UserRole userRoleDtoToModel(UserRoleDTO userRoleDTO);


  UserPrivilegeDTO userPrivilegeModelToDto(UserPrivilege userPrivilege);
  List<UserPrivilegeDTO> userPrivilegeModelToDtoList(List<UserPrivilege> userPrivileges);
  @Mapping(target = "id", ignore = true)
  UserPrivilege userPrivilegeDtoToModel(UserPrivilegeDTO userPrivilegeDTO);

}
