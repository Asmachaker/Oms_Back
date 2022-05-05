package com.demo.oms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Bordereau {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="client", nullable=false)
    private Client client;

    @OneToMany(targetEntity=Booking.class, fetch=FetchType.EAGER)
    @JoinColumn(name="bookings")
    private List<Booking> bookings;

    @OneToMany(mappedBy="bordereau")
    private List<FactureAvoir> factureAvoirs;

    @OneToMany(mappedBy="bordereau")
    private List<Facture> facture;

    @Column(updatable=false)
    private Date date ;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Booking> getBooking() {
        return bookings;
    }

    public void setBooking(List<Booking> booking) {
        this.bookings = booking;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
