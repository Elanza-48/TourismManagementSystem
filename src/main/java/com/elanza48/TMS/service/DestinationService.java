package com.elanza48.TMS.service;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.dao.DestinationRepository;
import com.elanza48.TMS.model.entity.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DestinationService {

  private DestinationRepository destinationRepository;
  @Autowired
  public void setDestinationRepository(DestinationRepository destinationRepository) {
    this.destinationRepository = destinationRepository;
  }

  public List<Destination> getAllDestinations(){
    return destinationRepository.findAll();
  }

  public Destination getDestinationByid(UUID id){
    return destinationRepository.findById(id).get();
  }

  public Destination createDestination(Destination destination){
    return destinationRepository.save(destination);
  }
  
}
