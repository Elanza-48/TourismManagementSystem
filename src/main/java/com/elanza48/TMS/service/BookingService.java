package com.elanza48.TMS.service;

import java.util.List;
import java.util.UUID;

import com.elanza48.TMS.model.dao.BookingRepository;
import com.elanza48.TMS.model.entity.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingService {

  private BookingRepository bookingRepository;

  @Autowired
  public void setBookingRepository(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  public List<Booking> getAllBookings(){
    return bookingRepository.findAll();
  }

  public Booking getBookingById(UUID id){
    return bookingRepository.findById(id).get();
  }

  public Booking createBooking(Booking booking){
    return bookingRepository.save(booking);
  }
}
