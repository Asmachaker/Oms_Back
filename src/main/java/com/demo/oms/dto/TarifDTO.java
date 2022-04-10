package com.demo.oms.dto;

public class TarifDTO {
     int id;
     float price;
     String zone;
     String taille;
     String shift;
     String name;

    public TarifDTO(int id, float price, String zone, String taille, String shift, String name) {
        this.id = id;
        this.price = price;
        this.zone = zone;
        this.taille = taille;
        this.shift = shift;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
