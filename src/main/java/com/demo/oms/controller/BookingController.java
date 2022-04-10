package com.demo.oms.controller;


import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;
import com.demo.oms.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;



    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;

    }
    
    @GetMapping("/getAllbookings")
    public ResponseEntity<List<Booking>> getAllbookings() {

        List<Booking> bookingList = bookingService.getAllBookings();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @PostMapping("/addBooking")
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking) {
        bookingService.addBooking(booking);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
