package com.demo.oms.service.impl;


import com.demo.oms.entity.Booking;
import com.demo.oms.repository.BookingRepository;
import com.demo.oms.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void addBooking(Booking booking) {
        bookingRepository.save(booking);

    }


}
