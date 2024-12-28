package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers;

import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.TranslateUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/translate")
public class TranslateController {

    private final TranslateUseCase translateUseCase;

    public TranslateController(TranslateUseCase translateUseCase) {
        this.translateUseCase = translateUseCase;
    }

    @PostMapping
    public ResponseEntity<String> translateText(@RequestBody String text) {
        String translatedText = translateUseCase.translate(text);
        return new ResponseEntity<>(translatedText, HttpStatus.OK);
    }

}
