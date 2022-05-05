package com.demo.oms.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="client", nullable=false)
    private Client customer;

    @Column(updatable=false)
    private Date date ;

    private String name;

    private boolean statut ;

    private double amount;

    @ManyToOne
    @JoinColumn(name="Bordereau", nullable=false)
    private Bordereau bordereau;

    public Client getClient() {
        return customer;
    }

    public void setClient(Client customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Bordereau getBordereau() {
        return bordereau;
    }

    public void setBordereau(Bordereau bordereau) {
        this.bordereau = bordereau;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
