package com.demo.oms.security;

import com.demo.oms.entity.AppUser;
import com.demo.oms.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AppUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, ResourceNotFoundException {

        final AppUser user=userRepo.findById(username).orElseThrow(()->new ResourceNotFoundException("Admin not found for this id :: " + username));
        return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }
}