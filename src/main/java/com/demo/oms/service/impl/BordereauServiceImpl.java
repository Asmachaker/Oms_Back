package com.demo.oms.service.impl;

import com.demo.oms.dto.FactureDTO;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;
import com.demo.oms.repository.BookingRepository;
import com.demo.oms.repository.BordereauRepository;
import com.demo.oms.repository.ClientRepository;
import com.demo.oms.service.BordereauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.time.LocalDate;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

@Service
public class BordereauServiceImpl implements BordereauService {

    @Autowired
    private BordereauRepository bordereauRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Bordereau> getAllBordereaus() {
        return bordereauRepository.findAll();
    }

    @Override
    public Bordereau getBordereau(Long id) {

        return bordereauRepository.findById(id).get();
    }
    @Override
    public List<Bordereau> generateBordereau()
    {
        List<Bordereau> borList = new ArrayList<Bordereau>();
        List<Client> clients = clientRepository.getEnabledClient();
        LocalDate now = LocalDate.now();
        LocalDate LastDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate FirstDate = LocalDate.now().withDayOfMonth(1);
        Date nowSqlDate = Date.valueOf(FirstDate);
        Date lastSqlDate = Date.valueOf(LastDate);

        for(Client client : clients) {
            List<Booking> bookings=bookingRepository.getBookingByDate(client,lastSqlDate,nowSqlDate);
            Long i= 1L;
        for(Booking booking :bookings)
        {
            booking.setNumCommande(i);
            i++;}
            Bordereau bordereau= new Bordereau();
            bordereau.setClient(client);
            bordereau.setBooking(bookings);
            bordereau.setDate(nowSqlDate);
            bordereauRepository.save(bordereau);
            borList.add(bordereau);


        }
        return borList;
    }

    @Override
    public Bordereau generateBordereauClient(Client client)
    {
        LocalDate now = LocalDate.now();
        LocalDate LastDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate FirstDate = LocalDate.now().plusDays(1);
        Date nowSqlDate = Date.valueOf(FirstDate);
        Date lastSqlDate = Date.valueOf(LastDate);
        Bordereau bordereau= new Bordereau();
        clientRepository.DisableClient(client.getId());
            List<Booking> bookings=bookingRepository.getBookingByDate(client,lastSqlDate,nowSqlDate);
            Long i= 1L;
            for(Booking booking :bookings)
            {
                booking.setNumCommande(i);
                i++;

            }
            bordereau.setClient(client);
            bordereau.setBooking(bookings);
            bordereau.setDate(Date.valueOf(now));

        return bordereauRepository.save(bordereau);
    }

    @Override
    public void deleteBordereau(Long id) {

        bordereauRepository.deleteById(id);
    }
}
