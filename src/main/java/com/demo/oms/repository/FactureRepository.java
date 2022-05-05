package com.demo.oms.repository;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FactureRepository extends JpaRepository<Facture,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Facture a " +
            "SET a.statut = TRUE WHERE a.id = ?1")
    int enableFacture(Long id);
}
