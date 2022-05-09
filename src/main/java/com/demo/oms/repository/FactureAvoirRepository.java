package com.demo.oms.repository;

import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;
import com.demo.oms.entity.FactureAvoir;
import com.demo.oms.entity.Taille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FactureAvoirRepository extends JpaRepository<FactureAvoir,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE FactureAvoir a " +
            "SET a.statut = TRUE WHERE a.id = ?1")
    int enableFactureAvoir(Long id);

    @Transactional
    @Query("SELECT u FROM FactureAvoir u WHERE u.bordereau = :bordereau")
    FactureAvoir getFactureByBordereau(
            @Param("bordereau") Bordereau bordereau);
}
