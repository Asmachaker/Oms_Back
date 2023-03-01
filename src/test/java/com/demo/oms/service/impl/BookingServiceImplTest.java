package com.demo.oms.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.demo.oms.dto.BookingDTO;
import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;
import com.demo.oms.entity.CodePostal;
import com.demo.oms.entity.Shift;
import com.demo.oms.entity.Taille;
import com.demo.oms.entity.Tarif;
import com.demo.oms.entity.Zone;
import com.demo.oms.repository.BookingRepository;
import com.demo.oms.repository.ClientRepository;
import com.demo.oms.repository.CodePostalRepository;
import com.demo.oms.repository.ShiftRepository;
import com.demo.oms.repository.TailleRepository;
import com.demo.oms.repository.TarifRepository;
import com.demo.oms.repository.ZoneRepository;
import com.demo.oms.util.CodeGenerator;
import com.demo.oms.util.Converter.BookingConverter;

@RunWith(MockitoJUnitRunner.class)
class BookingServiceImplTest {

	private Client client;

	@InjectMocks
	private final BookingServiceImpl bookingService = new BookingServiceImpl();

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private CodePostalRepository codePostalRepository;

	@Mock
	private ZoneRepository zoneRepository;

	@Mock
	private TailleRepository tailleRepository;

	@Mock
	private ShiftRepository shiftRepository;

	@Mock
	private TarifRepository tarifRepository;

	@Mock
	private BookingConverter bookingConverter;

	private List<Booking> bookings;

	private Booking booking1 = new Booking();
	private Booking booking2 = new Booking();
	private Booking booking;
	BookingDTO bookingDto = new BookingDTO();

	CodePostal code;
	Zone zone;
	Taille taille;
	Shift shift;
	Tarif tarif;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		client = new Client();
		client.setId("idclient");
		booking1.setClient(client);
		booking2 = new Booking();
		booking2.setClient(client);
		bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);

		bookingDto.setCodePostal(1);

		code = new CodePostal();
		code.setCode(1);
		code.setName("code");

		zone = new Zone();
		zone.setCode(code);
		zone.setName("zone");
		zone.setId(1);

		taille = new Taille();
		taille.setId("tailleId");
		taille.setName("taille");

		shift = new Shift();
		shift.setName("shift");

		tarif = new Tarif();
		tarif.setName("tarif");
		tarif.setPrice(15);
		tarif.setShift(shift);
		tarif.setTaille(taille);
		tarif.setZone(zone);

		booking = new Booking();
		booking.setClient(client);
		booking.setTarif(tarif);
		booking.setStatut("Pas Achevé");
	}

	@Test
	@Disabled
	void canGetBookingByDate() {
		final Date date = Mockito.mock(Date.class);
		when(clientRepository.findById(client.getId())).thenReturn(Optional.ofNullable(client));
		LocalDate LastDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
		LocalDate FirstDate = LocalDate.now().withDayOfMonth(1);
		Date nowSqlDate = java.sql.Date.valueOf(FirstDate);
		Date lastSqlDate = java.sql.Date.valueOf(LastDate);
		when(bookingRepository.getBookingByDate(client, date, date)).thenReturn(bookings);
		assertEquals(bookingService.getBookingByDate(client.getId()), bookings);
	}

	@Test
	void canConfirmBooking() {
		when(codePostalRepository.findById(bookingDto.getCodePostal())).thenReturn(Optional.ofNullable(code));
		when(zoneRepository.getZoneBycode(code)).thenReturn(zone);
		when(tailleRepository.getTailleByName(bookingDto.getTaille())).thenReturn(taille);
		when(shiftRepository.getShiftByName(bookingDto.getShift())).thenReturn(shift);
		when(tarifRepository.getTarifByAtt(zone, taille, shift)).thenReturn(tarif);
		when(clientRepository.findById(bookingDto.getIdClient())).thenReturn(Optional.ofNullable(client));
		when(bookingRepository.save(booking)).thenReturn(booking);
		assertEquals(bookingService.confirmBooking(bookingDto).getClient(), booking.getClient());
		assertEquals(bookingService.confirmBooking(bookingDto).getStatut(), booking.getStatut());
		assertEquals(bookingService.confirmBooking(bookingDto).getTarif().getName(), booking.getTarif().getName());
		assertEquals(bookingService.confirmBooking(bookingDto).getTarif().getPrice(), booking.getTarif().getPrice());
		assertEquals(bookingService.confirmBooking(bookingDto).getTarif().getTaille(), booking.getTarif().getTaille());
		assertEquals(bookingService.confirmBooking(bookingDto).getTarif().getZone(), booking.getTarif().getZone());
		assertEquals(bookingService.confirmBooking(bookingDto).getTarif().getShift(), booking.getTarif().getShift());
	}

}
