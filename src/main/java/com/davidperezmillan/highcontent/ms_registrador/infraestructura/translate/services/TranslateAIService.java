package com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.TranslatePort;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.models.TranslateResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class TranslateAIService implements TranslatePort {

    @Value("${translation.api.url}")
    private String API_URL;

    @Value("${translation.api.key}")
    private String API_KEY;

    private final RestTemplate restTemplate;

    @Autowired
    public TranslateAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String translate(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        TranslateResponse[] translateResponse = callApi(text);
        if (translateResponse.length == 0) {
            return "";
        }
        return translateResponse[0].getTranslationText();
    }

    public TranslateResponse[] callApi(String inputText) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL);
        String finalUrl = uriBuilder.toUriString();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        // recortar texto a 512 caracteres
        if (inputText.length() > 512) {
            inputText = inputText.substring(0, 512);
        }

        // Create the body with the "inputs" field
        Map<String, String> body = new HashMap<>();
        body.put("inputs", inputText);
        body.put("truncation", "only_first");

        try{
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<TranslateResponse[]> response = restTemplate.exchange(finalUrl, HttpMethod.POST, entity, TranslateResponse[].class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Error en la llamada a la API de traducción: {}", e.getMessage());
            return new TranslateResponse[0];
        }catch (HttpServerErrorException e) {
            log.error("Error en la llamada a la API de traducción: {}", e.getMessage());
            return new TranslateResponse[0];
        }
    }


}
