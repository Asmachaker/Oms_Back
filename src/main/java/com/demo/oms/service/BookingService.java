package com.demo.oms.service;

import com.demo.oms.dto.BookingDTO;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {


    List<Booking> getAllBookings();

    void addBooking(Booking booking);

    List<Booking> getBookingByDate (String client);

    Booking confirmBooking(BookingDTO bookingDTO);
}
