package com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.services;

import com.davidperezmillan.highcontent.ms_registrador.application.translate.ports.TranslatePort;
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
        String translateResponse = callApi(text);
        return !translateResponse.isEmpty() ? translateResponse : "";
    }

    public String callApi(String inputText) {

        StringBuilder response = new StringBuilder();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL);
        String finalUrl = uriBuilder.toUriString();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);


        log.info("url: {}, api-key: {}", finalUrl, API_KEY);


        // partir el texto en frases en caso de que sea muy largo

        String[] inputs = splitTextIntoChunks(inputText, 500);

        for (String input : inputs) {
            log.info("Peticion a la API de traducción: {}", input);
            // Create the body with the "inputs" field
            Map<String, String> body = new HashMap<>();
            body.put("inputs", input);
            body.put("truncation", "only_first");

            try {
                HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, httpHeaders);
                ResponseEntity<TranslateResponse[]> resp = restTemplate.exchange(finalUrl, HttpMethod.POST, entity, TranslateResponse[].class);
                response.append(resp.getBody()[0].getTranslationText());
            } catch (HttpClientErrorException e) {
                log.error("Error en la llamada a la API de traducción: {}", e.getMessage());
                return response.toString();
            } catch (HttpServerErrorException e) {
                log.error("Error en la llamada a la API de traducción: {}", e.getMessage());
                return response.toString();
            }
        }
        return response.toString();
    }


    private String[] splitTextIntoChunks(String text, int chunkSize) {
        int textLength = text.length();
        int arraySize = (int) Math.ceil((double) textLength / chunkSize);
        String[] chunks = new String[arraySize];

        for (int i = 0; i < arraySize; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, textLength);
            chunks[i] = text.substring(start, end);
        }

        return chunks;
    }

}
