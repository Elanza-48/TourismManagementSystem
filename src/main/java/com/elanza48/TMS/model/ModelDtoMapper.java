package com.elanza48.TMS.model;

import java.util.List;

import com.elanza48.TMS.model.dto.BookingDTO;
import com.elanza48.TMS.model.dto.DestinationDTO;
import com.elanza48.TMS.model.dto.HotelDTO;
import com.elanza48.TMS.model.dto.PackageDTO;
import com.elanza48.TMS.model.dto.UserAccountDTO;
import com.elanza48.TMS.model.entity.Booking;
import com.elanza48.TMS.model.entity.Destination;
import com.elanza48.TMS.model.entity.Hotel;
import com.elanza48.TMS.model.entity.Package;
import com.elanza48.TMS.model.entity.UserAccount;

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


  PackageDTO packageModelToDto(Package package1);
  List<PackageDTO> packageModelToDtoList(List<Package> packages);
  Package packageDtoToModel(PackageDTO packageDTO);


  BookingDTO bookingModelToDto(Booking booking);
  List<BookingDTO> bookingModelToDtoList(List<Booking> bookings);
  Booking bookingDtoToModel(BookingDTO bookingDTO);
  
}
