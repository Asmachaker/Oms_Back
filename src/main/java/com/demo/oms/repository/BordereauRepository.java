package com.demo.oms.repository;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BordereauRepository extends JpaRepository<Bordereau,Long> {
}
