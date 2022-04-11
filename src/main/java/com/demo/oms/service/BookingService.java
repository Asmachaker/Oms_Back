package com.demo.oms.service;

import com.demo.oms.dto.BookingDTO;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;

import java.util.List;

public interface BookingService {


    List<Booking> getAllBookings();

    void addBooking(Booking booking);

    Booking confirmBooking(BookingDTO bookingDTO);
}
