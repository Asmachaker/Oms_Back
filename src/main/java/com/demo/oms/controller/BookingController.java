package com.demo.oms.controller;


import com.demo.oms.dto.*;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;
import com.demo.oms.repository.BookingRepository;
import com.demo.oms.service.BookingService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @Autowired
    private BookingRepository bookingrep;



    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;

    }
    
    @GetMapping("/getAllbookings")
    public ResponseEntity<List<Booking>> getAllbookings() {

        List<Booking> bookingList = bookingService.getAllBookings();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @GetMapping("/getBooking/{id}")
    public ResponseEntity<List<Booking>> getAllbookingsByDate(@PathVariable String id) {

        List<Booking> bookingList = bookingService.getBookingByDate(id);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @PostMapping("/addBooking")
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking) {
        bookingService.addBooking(booking);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PostMapping("/confirmBooking")
    public ResponseEntity<Booking> confirmBooking(@RequestBody BookingDTO bookingDto) {
      Booking booking =  bookingService.confirmBooking(bookingDto);

        return new ResponseEntity<>( booking,HttpStatus.CREATED);
    }


    @GetMapping("/bookingChart")
    public ResponseEntity<List<BookingChartDTO>> booking() {
  //List<Booking> book=  bookingrep.getBookingBydate(Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(18)),Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(19)));

        return new ResponseEntity<>(bookingService.chartBooking(),HttpStatus.CREATED);
    }


    @GetMapping("/ReplanLivreur/{id}")
    public ResponseEntity<String> ReplanLivreur(@PathVariable Long id) {
      return new ResponseEntity<>(bookingService.ReplanLivreur(id), HttpStatus.OK);
    }

    @GetMapping("/ReplanClient/{id}")
    public ResponseEntity<String> ReplanClient(@PathVariable Long id) {
        return new ResponseEntity<>(bookingService.ReplanClient(id), HttpStatus.OK);
    }
    @PostMapping("/ReplanOms")
    public ResponseEntity<Boolean> ReplanOms(@RequestBody ReplanDTO replanDTO ) {
        Booking booking =bookingrep.findById(replanDTO.getId()).get();
        return new ResponseEntity<>(bookingService.ReplanOms(booking,replanDTO.getDate(),replanDTO.getShift()), HttpStatus.OK);
    }

    @GetMapping("/clientChart")
    public ResponseEntity<List<BookingClientDTO>> clientBooking() {
        //List<Booking> book=  bookingrep.getBookingBydate(Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(18)),Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(19)));

        return new ResponseEntity<>(bookingService.chartClient(),HttpStatus.CREATED);
    }
    @GetMapping("/zoneChart")
    public ResponseEntity<List<BookingZoneDTO>> zoneBooking() {
        //List<Booking> book=  bookingrep.getBookingBydate(Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(18)),Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(19)));

        return new ResponseEntity<>(bookingService.chartZone(),HttpStatus.CREATED);
    }


    @GetMapping("/box")
    public ResponseEntity<ElasticDTO[]> box() {
        //List<Booking> book=  bookingrep.getBookingBydate(Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(18)),Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(19)));

        return new ResponseEntity<>(bookingService.getBoxes(),HttpStatus.CREATED);
    }



}
