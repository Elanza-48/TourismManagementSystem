package com.elanza48.TMS.service;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.dao.DestinationRepository;
import com.elanza48.TMS.model.entity.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DestinationService {

  @Autowired
  private DestinationRepository destinationRepository;

  @Transactional(readOnly = true)
  public List<Destination> getAllDestinations(){
    return destinationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Destination getDestinationByid(UUID id){
    return destinationRepository.findById(id).get();
  }

  @Transactional
  public Destination createDestination(Destination destination){
    return destinationRepository.save(destination);
  }
  
}
