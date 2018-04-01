package com.distributedgame.userapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebGameService {
    private String serviceUrl = "http://localhost:5555/";

    // TODO: 01/04/18 app config also
//    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    public WebGameService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createKingdomsForUser(long userId) {
        Map<String, String> vars = new HashMap<>();
        vars.put("id", "JS01");

        this.restTemplate.postForEntity(serviceUrl + "kingdom/create-for-user/{userId}", vars, String.class);
    }
}
