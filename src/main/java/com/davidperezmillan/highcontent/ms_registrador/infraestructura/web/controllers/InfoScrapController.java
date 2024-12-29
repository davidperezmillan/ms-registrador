package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.GetListScrapUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.ScrapWebResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers.ScrapWebResponseMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/scrap")
public class InfoScrapController {

    private final GetListScrapUseCase getListScrapUseCase;

    public InfoScrapController(GetListScrapUseCase getListScrapUseCase) {
        this.getListScrapUseCase = getListScrapUseCase;
    }


    @GetMapping("/info")
    public ResponseEntity<ScrapWebResponse[]> getInfoScenes() {
        Scene[] resp = getListScrapUseCase.getScenes();
        log.info("Scenes info requested: {} elements", resp.length);
        ScrapWebResponse[] lista = ScrapWebResponseMapper.map(resp);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
