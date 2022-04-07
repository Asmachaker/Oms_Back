package com.demo.oms.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
public class CodePostal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer code;


    @ManyToOne
    @JoinColumn(name="zone_name", nullable=false)
    private Zone zone;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
