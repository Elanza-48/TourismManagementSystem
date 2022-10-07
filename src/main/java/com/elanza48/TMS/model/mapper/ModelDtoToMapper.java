package com.elanza48.TMS.model.mapper;

import java.util.List;

import com.elanza48.TMS.model.dto.*;
import com.elanza48.TMS.model.entity.*;

import com.elanza48.TMS.model.entity.Package;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ModelDtoToMapper {

  @Mapping(target = "password", ignore = true)
  UserAccountDTO userAccountModelToDto(UserAccount userAccount);
  List<UserAccountDTO> userAccountModelToDtoList(List<UserAccount> users);

  HotelDTO hotelModelToDto(Hotel hotel);
  List<HotelDTO> hotelModelToDtoList(List<Hotel> hotels);

  DestinationDTO destinationModelToDto(Destination dest);
  List<DestinationDTO> destinationModelToDtoList(List<Destination> dests);

  PackageDTO packageModelToDto(Package tourPackage);
  List<PackageDTO> packageModelToDtoList(List<Package> packages);

  BookingDTO bookingModelToDto(Booking booking);
  List<BookingDTO> bookingModelToDtoList(List<Booking> bookings);

  UserRoleDTO userRoleModelToDto(UserRole userRole);
  List<UserRoleDTO> userRoleModelToDtoList(List<UserRole> userRoles);

  UserPrivilegeDTO userPrivilegeModelToDto(UserPrivilege userPrivilege);
  List<UserPrivilegeDTO> userPrivilegeModelToDtoList(List<UserPrivilege> userPrivileges);

}
