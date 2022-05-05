package com.demo.oms.repository;

import com.demo.oms.entity.FactureAvoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FactureAvoirRepository extends JpaRepository<FactureAvoir,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE FactureAvoir a " +
            "SET a.statut = TRUE WHERE a.id = ?1")
    int enableFactureAvoir(Long id);
}
