package com.demo.oms.repository;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Client;
import com.demo.oms.entity.CodePostal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Transactional
    @Query("SELECT u FROM Booking u WHERE u.client = :client AND u.date BETWEEN :LastDate and :DateNow")
    List<Booking> getBookingByDate(
            @Param("client") Client client,
            @Param("LastDate") Date lastDate,
            @Param("DateNow") Date nowDate);
}
