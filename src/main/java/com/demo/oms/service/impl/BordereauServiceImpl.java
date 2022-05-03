package com.demo.oms.service.impl;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;
import com.demo.oms.repository.BookingRepository;
import com.demo.oms.repository.BordereauRepository;
import com.demo.oms.repository.ClientRepository;
import com.demo.oms.service.BordereauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;

import java.util.List;
import java.util.Set;

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
    public Bordereau generateBordereau()
    {
        List<Client> clients = clientRepository.getEnabledClient();
        LocalDate now = LocalDate.now();
        LocalDate LastDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate FirstDate = LocalDate.now().withDayOfMonth(1);
        Date nowSqlDate = Date.valueOf(FirstDate);
        Date lastSqlDate = Date.valueOf(LastDate);
        Bordereau bordereau= new Bordereau();
        for(Client client : clients) {
            List<Booking> bookings=bookingRepository.getBookingByDate(client,lastSqlDate,nowSqlDate);
            Long i= 1L;
        for(Booking booking :bookings)
        {
            booking.setNumCommande(i);
            i++;

        }
            bordereau.setClient(client);
           bordereau.setBooking(bookings);
            bordereau.setDate(nowSqlDate);

        }
        return bordereauRepository.save(bordereau);
    }

    @Override
    public void deleteBordereau(Long id) {

        bordereauRepository.deleteById(id);
    }
}
