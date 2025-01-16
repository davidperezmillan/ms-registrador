package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.media.controller;

import com.davidperezmillan.highcontent.ms_registrador.domain.media.models.Media;
import com.davidperezmillan.highcontent.ms_registrador.domain.media.usecases.SearchMediaUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.media.mappers.MediaResponseMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.media.models.MediaResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Media search and info
 */
@Log4j2
@RestController
@RequestMapping("/media")
public class InfoMediaController {


    private final SearchMediaUseCase searchMediaUseCase;

    public InfoMediaController( SearchMediaUseCase searchMediaUseCase) {
        this.searchMediaUseCase = searchMediaUseCase;
    }

    /**
     * Search media
     */
    @GetMapping("/search")
    public ResponseEntity<MediaResponse[]> getInfoScenes(@RequestParam String title) {
        Media[] resp = searchMediaUseCase.search(title);
        MediaResponse[] lista = MediaResponseMapper.map(resp);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
