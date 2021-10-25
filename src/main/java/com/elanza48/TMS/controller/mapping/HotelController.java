package com.elanza48.TMS.controller.mapping;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.entity.Hotel;
import com.elanza48.TMS.service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

  @Autowired
  private HotelService hotelService;

  @GetMapping
  public List<Hotel> getAllHotels(){
      return hotelService.getAllhotels();
  }

  @GetMapping("/{id}")
  public Hotel getHotelById(@PathVariable UUID id){
      return hotelService.getHotelById(id);
  }

  @PostMapping
  public Hotel insertHotel(@RequestBody Hotel body) {
      return hotelService.createHotel(body);
  }
  
}
