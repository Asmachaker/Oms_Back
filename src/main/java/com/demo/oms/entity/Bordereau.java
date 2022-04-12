package com.demo.oms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Bordereau {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="client", nullable=false)
    private Client client;

    @OneToMany(mappedBy="bordereau")
    private Set<Booking> booking;

    @Column(updatable=false)
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
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

    public Set<Booking> getBooking() {
        return booking;
    }

    public void setBooking(Set<Booking> booking) {
        this.booking = booking;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
