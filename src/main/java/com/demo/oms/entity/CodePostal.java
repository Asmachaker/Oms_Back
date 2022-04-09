package com.demo.oms.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;

@Entity
@Proxy(lazy = false)
public class CodePostal {
    @Id
    private int code;
    private String name;

    @OneToMany(mappedBy="code")
    private Set<Zone> Zone;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
