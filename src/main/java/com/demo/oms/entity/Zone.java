package com.demo.oms.entity;


import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Zone {

    @Id
    private int id ;
    private String name ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codePostal", referencedColumnName = "code")
    private CodePostal code;

    @OneToMany(mappedBy="zone")
    private Set<Tarif> tarifs;

    public Zone() {
    }

    public Zone(int id, String name, CodePostal code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

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

    public CodePostal getCode() {
        return code;
    }

    public void setCode(CodePostal code) {
        this.code = code;
    }
}
