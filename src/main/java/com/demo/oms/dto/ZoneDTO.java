package com.demo.oms.dto;

public class ZoneDTO {
    private int id ;
    private String name;
    private int CodePostal;

    public ZoneDTO(int id, String name, int codePostal) {
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

    public int getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(int codePostal) {
        CodePostal = codePostal;
    }
}
