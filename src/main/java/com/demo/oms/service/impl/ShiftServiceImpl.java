package com.demo.oms.service.impl;

import com.demo.oms.entity.Shift;
import com.demo.oms.repository.ShiftRepository;
import com.demo.oms.service.ShiftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 
@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;


    @Override
    public List<Shift> getAllShift() {
        return shiftRepository.findAll();
    }
}

