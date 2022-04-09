package com.demo.oms.dto;

public class ZoneDTO {
    private int id ;
    private String name;
    private String CodePostal;

    public ZoneDTO(int id, String name, String codePostal) {
        this.id = id;
        this.name = name;
        CodePostal = codePostal;
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

    public String getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(String codePostal) {
        CodePostal = codePostal;
    }
}
