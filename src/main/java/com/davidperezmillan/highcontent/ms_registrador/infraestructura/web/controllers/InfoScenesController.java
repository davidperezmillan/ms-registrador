package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.GetListScenesUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.SceneWebResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers.SceneWebResponseMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Info to scenes
 */
@Log4j2
@RestController
@RequestMapping("/scenes")
public class InfoScenesController {


    private final GetListScenesUseCase getListScenesUseCase;

    public InfoScenesController(GetListScenesUseCase getListScenesUseCase) {
        this.getListScenesUseCase = getListScenesUseCase;
    }

    /**
     * all scenes info
     */
    @GetMapping("/info")
    public ResponseEntity<SceneWebResponse[]> getInfoScenes() {
        Scene[] resp = getListScenesUseCase.getScenes();
        log.info("Scenes info requested: {} elements", resp.length);
        SceneWebResponse[] lista = SceneWebResponseMapper.map(resp);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
