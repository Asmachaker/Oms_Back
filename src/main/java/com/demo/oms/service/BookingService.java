package com.demo.oms.service;

import com.demo.oms.dto.*;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;

import java.sql.Date;
import java.time.LocalDate;
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

    Boolean ReplanOms(Booking booking, java.util.Date date, String shift);

    ElasticDTO[] getBoxes();

    String ReplanLivreur(Long idBooking);

    String ReplanClient(Long idBooking);

    List<BookingZoneDTO> chartZone();
}
