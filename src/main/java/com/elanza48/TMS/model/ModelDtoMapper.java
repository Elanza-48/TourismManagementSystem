package com.elanza48.TMS.model;

import java.util.List;

import com.elanza48.TMS.model.dto.*;
import com.elanza48.TMS.model.entity.*;

import com.elanza48.TMS.model.entity.Package;
import org.mapstruct.Mapper;

@Mapper
public interface ModelDtoMapper {
  
  UserAccountDTO userAccountModelToDto(UserAccount userAccount);
  List<UserAccountDTO> userAccountModelToDtoList(List<UserAccount> users);
  UserAccount UserAccountDtoToModel(UserAccountDTO userAccountDTO);

  
  HotelDTO hotelModelToDto(Hotel hotel);
  List<HotelDTO> hotelModelToDtoList(List<Hotel> hotels);
  Hotel hotelDtoToModel(HotelDTO hotelDto);


  DestinationDTO destinationModelToDto(Destination dest);
  List<DestinationDTO> destinationModelToDtoList(List<Destination> dests);
  Destination destinationDtoToModel(DestinationDTO destDto);


  PackageDTO packageModelToDto(Package tourPackage);
  List<PackageDTO> packageModelToDtoList(List<Package> packages);
  Package packageDtoToModel(PackageDTO packageDTO);


  BookingDTO bookingModelToDto(Booking booking);
  List<BookingDTO> bookingModelToDtoList(List<Booking> bookings);
  Booking bookingDtoToModel(BookingDTO bookingDTO);


  UserRoleDTO userRoleModelToDto(UserRole userRole);
  List<UserRoleDTO> userRoleModelToDtoList(List<UserRoleDTO> userRoles);
  UserRole userRoleDtoToModel(UserRoleDTO userRoleDTO);


  UserPrivilegeDTO userPrivilegeModelToDto(UserPrivilege userPrivilege);
  List<UserPrivilegeDTO> userPrivilegeModelToDtoList(List<UserPrivilege> userPrivileges);
  UserPrivilege userPrivilegeDtoToModel(UserPrivilegeDTO userPrivilegeDTO);

}
