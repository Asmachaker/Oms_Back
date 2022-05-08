package com.demo.oms.dto;

public class FactureDTO {
    String name;
    Integer quantity;
    Float prix ;
    Float total;

    public FactureDTO(String name, Integer quantity, Float prix, Float total) {
        this.name = name;
        this.quantity = quantity;
        this.prix = prix;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
