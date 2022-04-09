package com.demo.oms.repository;

import com.demo.oms.entity.CodePostal;
import com.demo.oms.entity.Tarif;
import com.demo.oms.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CodePostalRepository extends JpaRepository<CodePostal, Integer> {
    @Transactional
    @Query("SELECT u FROM CodePostal u WHERE u.name = :name")
    CodePostal getCodeByName(
            @Param("name") String name);
}
