package com.elanza48.TMS.controller.mapping;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.mapper.ModelDtoToMapper;
import com.elanza48.TMS.model.dto.DestinationDTO;
import com.elanza48.TMS.model.entity.Destination;
import com.elanza48.TMS.service.DestinationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/destination")
public class DestinationController {

  private DestinationService destinationService;
  private ModelDtoToMapper modelDtoToMapper;

  @Autowired
  public void setDestinationService(DestinationService destinationService) {
      this.destinationService = destinationService;
  }
  @Autowired
  public void setModelDtoToMapper(ModelDtoToMapper modelDtoToMapper) {
      this.modelDtoToMapper = modelDtoToMapper;
  }

  @GetMapping
  public List<DestinationDTO> getAllDestinations(){
      return modelDtoToMapper.destinationModelToDtoList(destinationService.getAllDestinations());
  }

  @GetMapping("/{id}")
  public DestinationDTO getDestinationById(@PathVariable UUID id){
      return modelDtoToMapper.destinationModelToDto(destinationService.getDestinationByid(id));
  }

  @PostMapping
  public Destination insertDestination(@RequestBody Destination body) {
      return destinationService.createDestination(body);
  }
  
}
