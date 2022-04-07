package com.demo.oms.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Taille {
    @Id
    private String id ;
    private String name ;
    @OneToMany(mappedBy="taille")
    private Set<Tarif> tarifs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public Set<Tarif> getTarifs() {
//        return tarifs;
//    }
//
//    public void setTarifs(Set<Tarif> tarifs) {
//        this.tarifs = tarifs;
//    }
}
