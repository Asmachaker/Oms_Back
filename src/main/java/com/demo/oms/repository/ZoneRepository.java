package com.demo.oms.repository;

import com.demo.oms.entity.Tarif;
import com.demo.oms.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {
}
