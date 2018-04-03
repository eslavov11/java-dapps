package com.distributedgame.userapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        this.restTemplate.postForEntity(serviceUrl + "kingdom/create-for-user/" + userId, request, String.class);
    }
}
