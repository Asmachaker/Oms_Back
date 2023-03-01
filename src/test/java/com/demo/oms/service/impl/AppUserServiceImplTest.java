package com.demo.oms.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.demo.oms.entity.AppUser;
import com.demo.oms.repository.AppUserRepository;

@RunWith(MockitoJUnitRunner.class)
class AppUserServiceImplTest {

	@InjectMocks
	private AppUserServiceImpl userService = new AppUserServiceImpl();

	@Mock
	private AppUserRepository appUserRepository;
	
	@Mock
	private EmailServiceImpl emailServiceImpl;
	
	private AppUser appUser = new AppUser();
	
	private AppUser appUser2 = new AppUser();
	
	private List<AppUser> users;
	
	@BeforeEach
	 void setUp() {
		MockitoAnnotations.initMocks(this); 
		appUser.setEmail("user@gmail.com");
		appUser.setFirstName("user");
		appUser.setPassword("password");
		appUser.setUsername("username");
		appUser.setEnabled(true);
		
		appUser2.setEmail("user2@gmail.com");
		appUser2.setFirstName("user2");
		appUser2.setPassword("password2");
		appUser2.setUsername("username2");
		appUser2.setEnabled(false);
		
		users = new ArrayList<>();
		users.add(appUser);
		users.add(appUser2);
	}
	
	@Test
	 void canEnableUserTest() {
		when(appUserRepository.enableAppUser(Mockito.anyString())).thenReturn(1);
		when(appUserRepository.findById(Mockito.anyString())).thenReturn(Optional.of(appUser));
		assertTrue(userService.EnableUser(appUser.getUsername()));
	}
	
	@Test
	 void canDisableUserTest() {
		when(appUserRepository.disableAppUser(Mockito.anyString())).thenReturn(1);
		assertTrue(userService.disableUser(appUser.getUsername()));
	}
	
	@Test
	 void canGetEnabledTest() {
		when(appUserRepository.userStatut(Mockito.anyString())).thenReturn(true);
		assertEquals(userService.GetEnabledByuser(appUser.getUsername()), appUser.getEnabled());
	}
	
	@Test
	 void canTGetEnabledTest() {
		appUser.setEnabled(false);
		when(appUserRepository.userStatut(Mockito.anyString())).thenReturn(false);
		assertEquals(userService.GetEnabledByuser(appUser.getUsername()), appUser.getEnabled());
	}
	
	@Test
	 void canGetAllUsers() {
		when(appUserRepository.getUser()).thenReturn(users);
		assertEquals(userService.getAllUsers().size(), users.size());
	}

}
