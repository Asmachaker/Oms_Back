package com.demo.oms.repository;

import com.demo.oms.entity.Taille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TailleRepository extends JpaRepository<Taille, String> {
}
