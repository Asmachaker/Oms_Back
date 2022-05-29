package com.demo.oms.dto;

import com.demo.oms.entity.Shift;

import java.util.Date;

public class ElasticDTO {
    Long idStation;
    Long idBox;
    Date date;
    String shift;

    public ElasticDTO(Long idStation, Long idBox, Date date, String shift) {
        this.idStation = idStation;
        this.idBox = idBox;
        this.date = date;
        this.shift = shift;
    }



    public Long getIdStation() {
        return idStation;
    }

    public void setIdStation(Long idStation) {
        this.idStation = idStation;
    }

    public Long getIdBox() {
        return idBox;
    }

    public void setIdBox(Long idBox) {
        this.idBox = idBox;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
