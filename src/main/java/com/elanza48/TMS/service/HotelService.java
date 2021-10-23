package com.elanza48.TMS.service;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.dao.HotelRepository;
import com.elanza48.TMS.model.dto.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelService {
  
  @Autowired
  private HotelRepository hotelRepository;

  @Transactional(readOnly = true)
  public List<Hotel> getAllhotels(){
    return hotelRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Hotel getHotelById(UUID id ){
    return hotelRepository.findById(id).get();
  }

  @Transactional
  public Hotel createHotel(Hotel hotel){
    return hotelRepository.save(hotel);
  }
}
