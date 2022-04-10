package com.demo.oms.repository;

import com.demo.oms.entity.Shift;
import com.demo.oms.entity.Taille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ShiftRepository extends JpaRepository<Shift, String> {

    @Transactional
    @Query("SELECT u FROM Shift u WHERE u.name = :name")
    Shift getShiftByName(
            @Param("name") String name);
}
