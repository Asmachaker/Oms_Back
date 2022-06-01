package com.demo.oms.service.impl;


import com.demo.oms.dto.*;
import com.demo.oms.entity.*;
import com.demo.oms.repository.*;
import com.demo.oms.service.BookingService;
import com.demo.oms.util.CodeGenerator;
import com.demo.oms.util.Converter.BookingConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
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
    public BookingChartDTO calcul(List<Booking> bookingList,Date date)
    {
        Integer i=0;
        for(Booking e:bookingList)
        {
            i=i+1;

        }
       return new BookingChartDTO(i,date);

    }

    @Override
    public List<BookingClientDTO> chartClient(){
        List<Client> clientList = clientRepository.getEnabledClient();
        List<BookingClientDTO> list = new ArrayList<BookingClientDTO>();

        for (Client c : clientList) {
            Date Day0 = Date.valueOf(LocalDate.now().plusDays(1));
            Date Day = Date.valueOf(LocalDate.now().withDayOfMonth(1));
            List<Booking> bookingList = bookingRepository.getBookingByDate(c,Day,Day0);
            Integer i=0;
            for(Booking b : bookingList){
                i=i+1;
            }
            BookingClientDTO booking = new BookingClientDTO(c,i);
            list.add(booking);
        }
        return list;
    }


    @Override
    public List<BookingChartDTO> chartBooking(){

        List<BookingChartDTO> list = new ArrayList<BookingChartDTO>();
//        Date Day0 = Date.valueOf(LocalDate.now().plusDays(1));
//        Date LastDay = Date.valueOf(LocalDate.now().minusDays(1));
//        Date day2 = Date.valueOf(LocalDate.now().minusDays(2));
//        Date day3 = Date.valueOf(LocalDate.now().minusDays(3));
    Date Day0 = Date.valueOf(LocalDate.now().minusDays(2));
    Date LastDay = Date.valueOf(LocalDate.now().minusDays(3));
    Date day2 = Date.valueOf(LocalDate.now().minusDays(4));
    Date day3 = Date.valueOf(LocalDate.now().minusDays(5));
        Date now = Date.valueOf(LocalDate.now());
        List<Booking> bookingListnnow =bookingRepository.getBookingBydate(now,Day0);
        List<Booking> bookingListLast =bookingRepository.getBookingBydate(LastDay,now);
        List<Booking> bookingList2 =bookingRepository.getBookingBydate(day2,LastDay);
        List<Booking> bookingList3 =bookingRepository.getBookingBydate(day3,day2);

       list.add(calcul(bookingListnnow,now));
       list.add(calcul(bookingListLast,LastDay));
       list.add(calcul(bookingList2,day2));
       list.add(calcul(bookingList3,day3));


     return list;
    }


    @Override
    public Boolean ReplanNow(Booking booking)
    {   boolean book=false;

        List<ElasticDTO> elastic =new ArrayList<ElasticDTO>();
        Date Day0 = Date.valueOf(LocalDate.now().plusDays(1));
        Date Day2 = Date.valueOf(LocalDate.now().plusDays(2));
        elastic.add(new ElasticDTO(100L,7L,Day0,"Matin"));
       elastic.add(new ElasticDTO(100L,4L,Day2,"Nuit"));
       for(ElasticDTO b :elastic)
       {
                if (b.getIdStation().equals(booking.getIdStation())) {
                    SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                    String DateElastic= DateFor.format(booking.getDate());
                    String DateBooking= DateFor.format(b.getDate());

                    if (DateElastic.equals(DateBooking)) {
                        if (b.getShift().equals(booking.getShift())) {
                            booking.setIdBox(b.getIdBox());
                            book=true;

                        }
                    }
                }
              }
          return book;
         }

    @Override
    public Boolean ReplanOms(Booking booking, java.util.Date date, String shift)
    {boolean book=false;
        String rep="";

        List<ElasticDTO> elastic =new ArrayList<ElasticDTO>();
        Date Day0 = Date.valueOf(LocalDate.now().plusDays(1));

        Date Day2 = Date.valueOf(LocalDate.now().plusDays(2));
        elastic.add(new ElasticDTO(100L,7L,Day0,"Matin"));
        elastic.add(new ElasticDTO(100L,4L,Day2,"Nuit"));
        for(ElasticDTO b :elastic)
        {
            if (b.getIdStation().equals(booking.getIdStation())) {
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                String DateElastic= DateFor.format(date);
                String DateBooking= DateFor.format(b.getDate());

                if (DateElastic.equals(DateBooking)) {
                    if (b.getShift().equals(shift)) {
                        book=true;
                        booking.setIdBox(b.getIdBox());
                        booking.setShift(shift);
                        booking.setDate(date);
                        bookingRepository.save(booking);

                    }
                }
            }
        }
        return book;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ElasticDTO[] getBoxes() {
        ResponseEntity resp = restTemplate.getForEntity("http://localhost:3000/box", ElasticDTO[].class);

        return resp.getStatusCode() == HttpStatus.OK ? (ElasticDTO[]) resp.getBody() : null;
    }

        @Override
        public String ReplanLivreur(Long idBooking) {
            java.util.Date data = null;
             Calendar c = Calendar.getInstance();
             boolean Replan = false;
             Booking booking = bookingRepository.findById(idBooking).get();
             String reponse;
             if (ReplanNow(booking)) {
                 reponse = "Replanification avec succés, Retapez ton delivery code";
             } else {
                 while (Replan == false) {
                     if ((booking.getShift()) == "Matin") {
                         booking.setShift("Nuit");
                         Replan = ReplanNow(booking);
                     } else {
                         java.util.Date date =booking.getDate();
                         c.setTime(date);
                         c.add(Calendar.DATE, 1);
                         data = c.getTime();
                         booking.setDate(data);
                         booking.setShift("Matin");
                         Replan = ReplanNow(booking);
                     }
                 }
                 reponse = "Ilya pas un box vide,le Booking est replanifié ";
             }
            booking.setDeliveryCode(CodeGenerator.getAlphaNumericString(8));
            booking.setPickupCode(CodeGenerator.getAlphaNumericString(8));
            booking.setTrackingCode(CodeGenerator.getAlphaNumericString(8));
            bookingRepository.save(booking);

            return reponse;
       }

    @Override
    public String ReplanClient(Long idBooking) {
        java.util.Date data = null;
        Calendar c = null;
        boolean Replan = false;
        Booking booking = bookingRepository.findById(idBooking).get();
        String reponse;

            while (Replan == false) {
                if ((booking.getShift()) == "Matin") {
                    booking.setShift("Nuit");
                    Replan = ReplanNow(booking);
                } else {
                    java.util.Date date =booking.getDate();
                    c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 1);
                    data = c.getTime();
                    booking.setDate(data);
                    booking.setShift("Matin");
                    Replan = ReplanNow(booking);
                }
            }
            reponse = "le Booking est replanifié ";
        booking.setDeliveryCode(CodeGenerator.getAlphaNumericString(8));
        booking.setPickupCode(CodeGenerator.getAlphaNumericString(8));
        booking.setTrackingCode(CodeGenerator.getAlphaNumericString(8));
        bookingRepository.save(booking);

        return reponse;
    }




@Override
    public List<BookingZoneDTO> chartZone() {
        List<Zone> zoneList = zoneRepository.findAll();
        List<BookingZoneDTO> list = new ArrayList<BookingZoneDTO>();

        for (Zone z : zoneList) {
            Date Day0 = Date.valueOf(LocalDate.now().plusDays(1));
            Date Day = Date.valueOf(LocalDate.now().withDayOfMonth(1));
            List<Booking> bookingList = bookingRepository.getBookingBydate(Day,Day0);
            Integer i=0;
            for(Booking b : bookingList){
                if (b.getTarif().getZone().getId() == z.getId())
                i=i+1;
            }
            BookingZoneDTO booking = new BookingZoneDTO(z,i);
            list.add(booking);
        }
        return list;


    }
}
