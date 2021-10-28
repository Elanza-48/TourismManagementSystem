package com.elanza48.TMS.service;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.dao.BookingRepository;
import com.elanza48.TMS.model.entity.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

  @Autowired
  private BookingRepository bookingRepository;

  @Transactional(readOnly = true)
  public List<Booking> getAllBookings(){
    return bookingRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Booking getBookingById(UUID id){
    return bookingRepository.findById(id).get();
  }

  @Transactional
  public Booking cr(Booking booking){
    return bookingRepository.save(booking);
  }
}
