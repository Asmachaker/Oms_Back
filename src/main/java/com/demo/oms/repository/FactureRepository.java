package com.demo.oms.repository;

import com.demo.oms.entity.Booking;
import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Facture;
import com.demo.oms.entity.FactureAvoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface FactureRepository extends JpaRepository<Facture,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Facture a " +
            "SET a.statut = TRUE WHERE a.id = ?1")
    int enableFacture(Long id);

    @Transactional
    @Query("SELECT u FROM Facture u WHERE u.bordereau = :bordereau")
    Facture getFactureByBordereau(
            @Param("bordereau") Bordereau bordereau);
}
