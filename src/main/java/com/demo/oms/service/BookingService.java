package com.demo.oms.service;

import com.demo.oms.dto.BookingChartDTO;
import com.demo.oms.dto.BookingClientDTO;
import com.demo.oms.dto.BookingDTO;
import com.demo.oms.dto.BookingZoneDTO;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BookingService {


    List<Booking> getAllBookings();

    void addBooking(Booking booking);

    List<Booking> getBookingByDate (String client);

    Booking confirmBooking(BookingDTO bookingDTO);

    List<BookingClientDTO> chartClient();

    List<BookingChartDTO> chartBooking();

    Boolean ReplanNow(Booking booking);

    Date ReplanLivreur(Long idBooking);

    String ReplanClient(Long idBooking);

    Booking bookingTest(Booking booking);

    List<BookingZoneDTO> chartZone();
}
