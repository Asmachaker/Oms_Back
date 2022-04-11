package com.demo.oms.service;

import com.demo.oms.dto.ZoneDTO;
import com.demo.oms.entity.Zone;

import java.util.List;

public interface ZoneService {


    void addZone(ZoneDTO zone);

    List<Zone> getAllZone();

    Zone getZone(Integer id);

    Zone getZoneCode(Integer id);

    void UpdateZone(ZoneDTO zone);

    void deleteZone(Integer id);
}
