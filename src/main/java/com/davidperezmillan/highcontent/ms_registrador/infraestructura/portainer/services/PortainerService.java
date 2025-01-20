package com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.services;

import com.davidperezmillan.highcontent.ms_registrador.application.portainer.PortainerPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.mappers.ContainerPortainerResponseMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.models.ContainerPortainerResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.models.StatusEnum;
import lombok.extern.log4j.Log4j2;
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

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PortainerService  implements PortainerPort {

    @Value("${portainer.api.url}")
    private String API_URL;

    @Value("${portainer.api.key}")
    private String API_KEY;

    private final RestTemplate restTemplate;

    public PortainerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Container[] getContainer() {
        return  ContainerPortainerResponseMapper.map(getAllContainers());
    }

    @Override
    public Container[] getContainerStop() {
        return ContainerPortainerResponseMapper.map(getContainersByEstado(StatusEnum.EXITED));
    }

    @Override
    public void startContainer(String containerId) {
        startContainerByID(containerId);
    }

    private ContainerPortainerResponse[] getContainersByEstado(StatusEnum estado){
        // filtramos los contenedores que estén en estado EXITED
        return Arrays.stream(getAllContainers())
                .filter(containerPortainerResponse -> containerPortainerResponse.getState().equals(estado.getValue()))
                .collect(Collectors.toList())
                .toArray(new ContainerPortainerResponse[0]);
    }

    private ContainerPortainerResponse[] getAllContainers() {
        // crear la llamada a la API de Portainer
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL + "/api/endpoints/2/docker/containers/json")
                .queryParam("all", true);

        String finalUrl = uriBuilder.toUriString();

        HttpHeaders httpHeaders = new HttpHeaders();
        // acceptamos JSON
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("X-API-Key", API_KEY);

        try {
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(httpHeaders);
            ResponseEntity<ContainerPortainerResponse[]> resp = restTemplate.exchange(finalUrl, HttpMethod.GET, entity, ContainerPortainerResponse[].class);
            //log.info("Respuesta de la API de Portainer: {}", resp.getBody());
            return resp.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Error en la llamada a la API de portainer: {}", e.getMessage());
            throw e;
        } catch (HttpServerErrorException e) {
            log.error("Error en la llamada a la API de portainer: {}", e.getMessage());
            throw e;
        }
    }



    private void startContainerByID(String containerId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL + "/api/endpoints/2/docker/containers/" + containerId + "/start");
        String finalUrl = uriBuilder.toUriString();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("X-API-Key", API_KEY);

        log.info("url: {}, api-key: {}", finalUrl, API_KEY);

        try {
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(httpHeaders);
            restTemplate.exchange(finalUrl, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error en la llamada a la API de traducción: {}", e.getMessage());
            throw e;
        } catch (HttpServerErrorException e) {
            log.error("Error en la llamada a la API de traducción: {}", e.getMessage());
            throw e;
        }
    }



}
