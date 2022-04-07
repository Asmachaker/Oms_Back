package com.demo.oms.service;

import com.demo.oms.entity.Zone;

import java.util.List;

public interface ZoneService {
    void addZone(Zone zone);

    List<Zone> getAllZone();

    Zone getZone(Integer id);

    void UpdateZone(Zone zone);

    void deleteZone(Integer id);
}
