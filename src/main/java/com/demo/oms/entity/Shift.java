package com.demo.oms.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shift {
    @Id
    private String name ;

    @OneToMany(mappedBy="shift")
    private Set<Tarif> tarifs;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
