package com.demo.oms.service.impl;

import com.demo.oms.entity.Bordereau;
import com.demo.oms.entity.Client;
import com.demo.oms.repository.BordereauRepository;
import com.demo.oms.repository.ClientRepository;
import com.demo.oms.service.BordereauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BordereauServiceImpl implements BordereauService {

    @Autowired
    private BordereauRepository bordereauRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Bordereau> getAllBordereaus() {
        return bordereauRepository.findAll();
    }

    public void generateBordereau()
    {
        List<Client> client = clientRepository.getEnabledClient();

    }


}
