package com.davidperezmillan.highcontent.ms_registrador.infraestructura.player.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.PlayerPort;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.player.models.UserDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class JellyfinService implements PlayerPort {


    private final RestTemplate restTemplate;

    @Autowired
    public JellyfinService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDtos[] getUser() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://192.168.1.195:8096/Users");
//        Map<String, String> queryParams = new HashMap<>();
//        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
//            uriBuilder.queryParam(entry.getKey(), entry.getValue());
//        }
        String finalUrl = uriBuilder.toUriString();

        HttpHeaders httpHeaders = new HttpHeaders();

        HashMap<String, String> headers = new HashMap<>();
        headers.put("X-MediaBrowser-Token", "0d75e5f1621f4e5aa56a91f07ec59c21");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpHeaders.set(entry.getKey(), entry.getValue());
        }

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<UserDtos[]> response = restTemplate.exchange(finalUrl, HttpMethod.GET, entity, UserDtos[].class);
        return response.getBody();
    }

}
