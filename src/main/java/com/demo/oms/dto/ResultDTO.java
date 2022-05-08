package com.demo.oms.dto;

import com.demo.oms.entity.Client;

import java.util.Map;

public class ResultDTO {
    private Map<Float, Integer> List;
    private Client client;

    public ResultDTO(Map<Float, Integer> list, Client client) {
        List = list;
        this.client = client;
    }

    public Map<Float, Integer> getList() {
        return List;
    }

    public Client getClient() {
        return client;
    }
}