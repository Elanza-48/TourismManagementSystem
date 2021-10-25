package com.elanza48.TMS.controller.mapping;

import java.util.List;
import java.util.UUID;

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

  @Autowired
  private DestinationService destinationService;

  @GetMapping
  public List<Destination> getAllDestinations(){
      return destinationService.getAllDestinations();
  }

  @GetMapping("/{id}")
  public Destination getDestinationById(@PathVariable UUID id){
      return destinationService.getDestinationByid(id);
  }

  @PostMapping
  public Destination insertDestination(@RequestBody Destination body) {
      return destinationService.createDestination(body);
  }
  
}
