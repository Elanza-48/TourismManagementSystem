package com.elanza48.TMS.model.mapper;

import java.util.List;

import com.elanza48.TMS.model.dto.BookingDTO;
import com.elanza48.TMS.model.entity.Booking;

import org.mapstruct.Mapper;

@Mapper
public interface BookingMapper {

  BookingDTO bookingModelToDto(Booking booking);
  List<BookingDTO> bookingModelToDtoList(List<Booking> bookings);
  Booking bookingDtoToModel(BookingDTO bookingDTO);
}
