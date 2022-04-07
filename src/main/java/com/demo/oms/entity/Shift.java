package com.demo.oms.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;

    @OneToMany(mappedBy="shift")
    private Set<Tarif> tarifs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
