package com.demo.oms.repository;

import com.demo.oms.entity.AppUser;
import com.demo.oms.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {


    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = false WHERE a.username = ?1")
    int disableAppUser(String id);


    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = true WHERE a.username = ?1")
    int enableAppUser(String id);



    @Transactional
    @Query("SELECT enabled " +
            "FROM AppUser WHERE username = ?1")
    boolean userStatut(String id);

    @Transactional
    @Query("SELECT password " +
            "FROM AppUser WHERE username = ?1")
    String oldMdp(String id);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.password = ?1 WHERE a.username = ?2")
    int resetPassword(String password,String id);

    @Transactional
    @Query("SELECT username FROM AppUser WHERE email = ?1")
    String emailSearch(String id);

    @Transactional
    @Query("SELECT u FROM AppUser u WHERE u.appUserRole = 'USER'")
    List<AppUser> getUser();

//    @Transactional
//    @Query("SELECT * " +
//            "FROM AppUser WHERE" +
//            "role ='USER'")
//    List<AppUser> allUser();
//




}
