package com.demo.oms.dto;

import com.demo.oms.entity.Client;

public class BookingClientDTO {
    private Client client;
    private Integer bookings;

    public BookingClientDTO(Client client, Integer bookings) {
        this.client = client;
        this.bookings = bookings;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getBookings() {
        return bookings;
    }

    public void setBookings(Integer bookings) {
        this.bookings = bookings;
    }
}
