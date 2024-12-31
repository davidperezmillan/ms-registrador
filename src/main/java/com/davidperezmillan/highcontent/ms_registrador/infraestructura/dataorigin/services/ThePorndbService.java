package com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.DataOriginPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.mappers.DataResponseMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models.DataResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models.SceneResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class ThePorndbService implements DataOriginPort {

    @Value("${the-porndb.api.url}")
    private String API_URL;

    @Value("${the-porndb.api.key}")
    private String API_KEY;

    private final RestTemplate restTemplate;

    public ThePorndbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Scene[] getAllScenes() {
        DataResponse[] data = callApi().getData();
        // filtrar por tag
        data = filterByTag(data);
        return DataResponseMapper.map(data);
    }



    public SceneResponse callApi() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL);
        String finalUrl = uriBuilder.toUriString();

        HttpHeaders httpHeaders = new HttpHeaders();

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", API_KEY);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpHeaders.set(entry.getKey(), entry.getValue());
        }

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<SceneResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, entity, SceneResponse.class);
        return response.getBody();
    }

    private DataResponse[] filterByTag(DataResponse[] data) {
        String[] excludedTags = {"gay", "Twink"}; // Define los tags a excluir

        data = Arrays.stream(data)
                .filter(d -> Arrays.stream(d.getTags())
                        .noneMatch(tag -> Arrays.asList(excludedTags).contains(tag.getName().toLowerCase())))
                .toArray(DataResponse[]::new);
        return data;
    }

}
