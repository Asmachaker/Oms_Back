package com.demo.oms.service.impl;


import com.demo.oms.dto.BookingDTO;
import com.demo.oms.entity.*;
import com.demo.oms.repository.*;
import com.demo.oms.service.BookingService;
import com.demo.oms.util.CodeGenerator;
import com.demo.oms.util.Converter.BookingConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private TailleRepository tailleRepository;

    @Autowired
    private CodePostalRepository codeRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private TarifRepository tarifRepository;


    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }


    @Override
    public List<Booking> getBookingByDate(String idclient)
    {
        Client client = clientRepository.findById(idclient).get();
        LocalDate now = LocalDate.now();
        LocalDate LastDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate FirstDate = LocalDate.now().withDayOfMonth(1);
        Date nowSqlDate = Date.valueOf(FirstDate);
        Date lastSqlDate = Date.valueOf(LastDate);
        List<Booking> bookings=bookingRepository.getBookingByDate(client,lastSqlDate,nowSqlDate);
        return bookings;
    }

    @Override
    public Booking confirmBooking (BookingDTO bookingDTO)
    {
         CodePostal code = codeRepository.findById(bookingDTO.getCodePostal()).get();
         Zone zone = zoneRepository.getZoneBycode(code);
         Taille taille= tailleRepository.getTailleByName(bookingDTO.getTaille());
         Shift shift = shiftRepository.getShiftByName(bookingDTO.getShift());
         Tarif tarif = tarifRepository.getTarifByAtt(zone,taille,shift);
         Client client = clientRepository.findById(bookingDTO.getIdClient()).get();

       Booking booking = BookingConverter.convertDtoToEntity(bookingDTO);
       booking.setClient(client);
       booking.setTarif(tarif);
       booking.setStatut("Pas Achevé");
       booking.setDeliveryCode(CodeGenerator.getAlphaNumericString(8));
       booking.setPickupCode(CodeGenerator.getAlphaNumericString(8));
       booking.setTrackingCode(CodeGenerator.getAlphaNumericString(8));
       bookingRepository.save(booking);



        return  booking;
    }


}
