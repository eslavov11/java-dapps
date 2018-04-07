package com.distributedgame.userapp.service;

import com.distributedgame.dataaccess.model.viewmodel.KingdomViewModel;
import com.distributedgame.dataaccess.model.viewmodel.UserViewModel;
import com.distributedgame.dataaccess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.distributedgame.userapp.util.Utils.serializeJSON;

@Service
public class WebGameService {
    private String serviceUrl = "http://game-app/";
    private SimpMessagingTemplate messagingTemplate;

    private RestTemplate restTemplate;

    @Autowired
    public WebGameService(RestTemplate restTemplate,
                          SimpMessagingTemplate messagingTemplate) {
        this.restTemplate = restTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    @Async
    public void createKingdomsForUser(UserViewModel userViewModel) {
        HttpEntity<List<KingdomViewModel>> entity = this.restTemplate
                .exchange(serviceUrl + "kingdom/create-for-user/" +
                                userViewModel.getId(),
                        HttpMethod.POST,
                        null,
                        new ParameterizedTypeReference<List<KingdomViewModel>>() {
                        });

        userViewModel.setKingdoms(entity.getBody());

        this.messagingTemplate
                .convertAndSend("/topic/profile",
                        serializeJSON(userViewModel, false));
    }
}
