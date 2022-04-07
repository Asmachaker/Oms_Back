package com.demo.oms.entity;

import javax.persistence.*;

@Entity
public class Tarif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private float price;

    @ManyToOne
    @JoinColumn(name="shift", nullable=false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(name="zone", nullable=false)
    private Zone zone;

    @ManyToOne
    @JoinColumn(name="taille", nullable=false)
    private Taille taille;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }
}
