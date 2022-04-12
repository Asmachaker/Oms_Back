package com.demo.oms.repository;


import com.demo.oms.entity.Client;
import com.demo.oms.entity.CodePostal;
import com.demo.oms.entity.Taille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    @Transactional
    @Modifying
    @Query("UPDATE Client a " +
            "SET a.statut = TRUE WHERE a.id = ?1")
    int enableClient(String id);

    @Transactional
    @Modifying
    @Query("UPDATE Client a " +
            "SET a.statut = FALSE WHERE a.id = ?1")
    int DisableClient(String id);

    @Transactional
    @Query("SELECT u FROM Client u WHERE u.statut = TRUE")
    List<Client> getEnabledClient();




}

