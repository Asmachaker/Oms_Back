package com.demo.oms.dto;

import com.demo.oms.entity.Zone;

public class BookingZoneDTO {
    private Zone zone;
    private Integer integer ;

    public BookingZoneDTO(Zone zone, Integer integer) {
        this.zone = zone;
        this.integer = integer;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }
}
