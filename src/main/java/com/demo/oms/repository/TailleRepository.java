package com.demo.oms.repository;

import com.demo.oms.entity.CodePostal;
import com.demo.oms.entity.Taille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TailleRepository extends JpaRepository<Taille, String> {
    @Transactional
    @Query("SELECT u FROM Taille u WHERE u.name = :name")
    Taille getTailleByName(
            @Param("name") String name);
}

