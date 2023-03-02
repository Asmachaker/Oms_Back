package com.demo.oms.service.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.demo.oms.entity.AppUser;
import com.demo.oms.repository.AppUserRepository;


@RunWith(MockitoJUnitRunner.class)
class JwtUserDetailsServiceTest {
	
	@InjectMocks
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Mock
    private AppUserRepository appUserRepository;

	private AppUser appUser;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); 
		appUser = new AppUser();
		appUser.setEmail("user@gmail.com");
		appUser.setFirstName("user");
		appUser.setPassword("password");
		appUser.setUsername("username");
		appUser.setEnabled(true);
	}

	@Test
	void canLoadUserByUsernameTest() {
		when(appUserRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(appUser));
		assertEquals(jwtUserDetailsService.loadUserByUsername(Mockito.anyString()).getUsername(), appUser.getUsername());
		assertEquals(jwtUserDetailsService.loadUserByUsername(Mockito.anyString()).getPassword(), appUser.getPassword());
		assertEquals(jwtUserDetailsService.loadUserByUsername(Mockito.anyString()).getAuthorities().size(), 0);
	}
	
	@Test
	void canTLoadUserByUsernameTest() {
		when(appUserRepository.findById(Mockito.anyString())).thenThrow(new UsernameNotFoundException("Username not valid"));
		
		assertThrows(UsernameNotFoundException.class,
				()->jwtUserDetailsService.loadUserByUsername(Mockito.anyString()));
	}

}
