package com.elanza48.TMS.controller.mapping;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.ModelDtoMapper;
import com.elanza48.TMS.model.dto.HotelDTO;
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

  private HotelService hotelService;
  private ModelDtoMapper modelDtoMapper;

  @Autowired
  public void setHotelService(HotelService hotelService) {
      this.hotelService = hotelService;
  }

  @Autowired
    public void setModelDtoMapper(ModelDtoMapper modelDtoMapper) {
        this.modelDtoMapper = modelDtoMapper;
    }

    @GetMapping
  public List<HotelDTO> getAllHotels(){
      return modelDtoMapper.hotelModelToDtoList(hotelService.getAllhotels());
  }

  @GetMapping("/{id}")
  public HotelDTO getHotelById(@PathVariable UUID id){
      return modelDtoMapper.hotelModelToDto(hotelService.getHotelById(id));
  }

  @PostMapping
  public Hotel insertHotel(@RequestBody Hotel body) {
      return hotelService.createHotel(body);
  }
  
}
