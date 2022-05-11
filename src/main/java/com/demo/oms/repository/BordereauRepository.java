package com.demo.oms.repository;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.border.Border;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface BordereauRepository extends JpaRepository<Bordereau,Long> {

    @Transactional
    @Query("SELECT u FROM Bordereau u WHERE u.date = :DateNow")
    List<Bordereau> getBordereauBydate(
            @Param("DateNow") Date nowDate);
}
