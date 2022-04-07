package com.demo.oms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    private String id;
    private Boolean statut;
    private Long phoneNumber;
    private String email;
    private String worning;
    private String address;
    private Long taxNumber;
    private String gouvernorat;
    private int deadlinePaiment;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }


    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorning() {
        return worning;
    }

    public void setWorning(String worning) {
        this.worning = worning;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(Long taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public int getDeadlinePaiment() {
        return deadlinePaiment;
    }

    public void setDeadlinePaiment(int deadlinePaiment) {
        this.deadlinePaiment = deadlinePaiment;
    }
}