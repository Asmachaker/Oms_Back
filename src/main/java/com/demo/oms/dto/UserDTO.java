package com.demo.oms.dto;

import com.demo.oms.entity.AppUser;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;

    public UserDTO(AppUser appUser) {

    }
}
