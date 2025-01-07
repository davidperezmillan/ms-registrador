package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.series.controller;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.series.models.SeriesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/serie")
public class ScrapSeriesController {


    @PostMapping
    public ResponseEntity<List<SeriesResponse>> findAndSave() {
        // sanitize text
        log.info("Finding and saving series");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
