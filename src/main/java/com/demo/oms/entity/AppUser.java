package com.demo.oms.entity;


import com.demo.oms.entity.Enum.AppUserRole;
import lombok.*;
import org.hibernate.annotations.Proxy;


import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Proxy(lazy = false)


public class AppUser{

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phoneNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean enabled = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
