package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.services;

import com.davidperezmillan.highcontent.ms_registrador.application.media.ports.MediaPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.media.models.Media;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.mappers.mappers.SearchResponseMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.FichaResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.SearchResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Log4j2
@Service
public class PlaymaxService implements MediaPort {

    private final RestTemplate restTemplate;

    public PlaymaxService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Media[] search(String title) {

        String url = "https://playmax.mx/api/{returnFormat}/get/search/v1/{apiKey}/{sessionId}/";
        String returnFormat = "json";
        String apikey = "5d0249e059e1b250cc915c94";
        String sessionId = "44eff70e8b979e06194347a45c07f1e761436d93";

        // Build the URL with the query parameter
        try {
            String uri = UriComponentsBuilder.fromUriString(url)
                    .queryParam("query", title)
                    .buildAndExpand(returnFormat, apikey, sessionId)
                    .toUriString();
            ResponseEntity<SearchResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, SearchResponse.class);
            SearchResponse searchResponse = response.getBody();


            log.info("Response: {}", response);
            return SearchResponseMapper.map(searchResponse.getResult().getFicha().getFichas());
        } catch (HttpClientErrorException e) {
            log.error("Error response: {}", e.getResponseBodyAsString());
            throw e;
        }
    }

    public Media getFicha(long id){
        String url = "https://playmax.mx/api/{returnFormat}/get/ficha/v1.2/{apiKey}/{sessionId}/";
        String returnFormat = "json";
        String apikey = "5d0249e059e1b250cc915c94";
        String sessionId = "44eff70e8b979e06194347a45c07f1e761436d93";

        // Build the URL with the query parameter
        try {
            String uri = UriComponentsBuilder.fromUriString(url)
                    .queryParam("fichaId", id)
                    .buildAndExpand(returnFormat, apikey, sessionId)
                    .toUriString();

            ResponseEntity<FichaResponse> response =restTemplate.exchange(uri, HttpMethod.GET, null, FichaResponse.class);
            // añadir un campo en la respuesta que sea el id de la ficha
            FichaResponse fichaResponse = response.getBody();
            Info info = fichaResponse.getResult().getInfo();

            log.info("Response: {}", response);
            return SearchResponseMapper.map(info);
        } catch (HttpClientErrorException e) {
            log.error("Error response: {}", e.getResponseBodyAsString());
            throw e;
        }
    }
}

