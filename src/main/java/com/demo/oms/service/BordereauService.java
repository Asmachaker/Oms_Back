package com.demo.oms.service;

import com.demo.oms.entity.Bordereau;

import java.util.List;

public interface BordereauService {
    List<Bordereau> getAllBordereaus();

    void generateBordereau();
}
