package com.demo.oms.repository;

import com.demo.oms.entity.CodePostal;
import com.demo.oms.entity.Taille;
import com.demo.oms.entity.Tarif;
import com.demo.oms.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    @Transactional
    @Query("SELECT u FROM Zone u WHERE u.name = :name")
    Zone getZoneByName(
            @Param("name") String name);

    @Transactional
    @Query("SELECT u FROM Zone u WHERE u.code = :code")
    Zone getZoneBycode(
            @Param("code") CodePostal code);
}
