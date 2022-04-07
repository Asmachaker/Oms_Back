package com.demo.oms.entity;


import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;

    @OneToMany(mappedBy="zone", fetch = FetchType.EAGER)
    private Set<CodePostal> codes;

    @OneToMany(mappedBy="zone")
    private Set<Tarif> tarifs;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CodePostal> getCodes() {
        return codes;
    }

    public void setCodes(Set<CodePostal> codes) {
        this.codes = codes;
    }


}
