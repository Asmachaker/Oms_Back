package com.demo.oms.service.impl;

import com.demo.oms.entity.AppUser;
import com.demo.oms.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private AppUserRepository appUserRepository;


    public JwtUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        AppUser user = appUserRepository.findById(username).get();
        if (user != null){

           return new User(user.getUsername()
                     , user.getPassword(),new ArrayList<>());}
        else throw new UsernameNotFoundException("Username not valid");
    }




}
