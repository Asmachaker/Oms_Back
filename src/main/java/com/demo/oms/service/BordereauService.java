package com.demo.oms.service;

import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;

import java.util.List;

public interface BordereauService {
    List<Bordereau> getAllBordereaus();

    Bordereau getBordereau(Long id);

    Bordereau generateBordereau();

    Bordereau generateBordereauClient(Client client);

    void deleteBordereau(Long id);
}
