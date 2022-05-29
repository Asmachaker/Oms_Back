package com.demo.oms.dto;

import java.util.Date;

public class BookingChartDTO {

    Integer number;
    Date date;

    public BookingChartDTO(int number, Date date) {
        this.number = number;
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
