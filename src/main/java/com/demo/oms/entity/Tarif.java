package com.demo.oms.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Tarif {

    @Id
    private Integer id ;
    private String name;
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

    @OneToMany(mappedBy="tarif")
    private Set<Booking> booking;


    public Tarif() {
    }

    public Tarif(Integer id, String name, float price, Shift shift, Zone zone, Taille taille) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shift = shift;
        this.zone = zone;
        this.taille = taille;
    }

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarif tarif = (Tarif) o;
        return Float.compare(tarif.price, price) == 0 && Objects.equals(id, tarif.id) && Objects.equals(name, tarif.name) && Objects.equals(shift, tarif.shift) && Objects.equals(zone, tarif.zone) && Objects.equals(taille, tarif.taille) && Objects.equals(booking, tarif.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, shift, zone, taille, booking);
    }
}
