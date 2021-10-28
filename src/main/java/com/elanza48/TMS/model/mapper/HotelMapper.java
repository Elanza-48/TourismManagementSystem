package com.elanza48.TMS.model.mapper;

import java.util.List;

import com.elanza48.TMS.model.dto.HotelDTO;
import com.elanza48.TMS.model.entity.Hotel;

import org.mapstruct.Mapper;

@Mapper
public interface HotelMapper {

  HotelDTO hotelModelToDto(Hotel hotel);
  List<HotelDTO> hotelModelToDtoList(List<Hotel> hotels);
  Hotel hotelDtoToModel(HotelDTO hotelDto);
  
}
