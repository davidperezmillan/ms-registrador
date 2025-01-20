package com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.services;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.config.RestTemplateConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest(classes = { TranslateAIService.class, RestTemplateConfig.class })
class TranslateAIServiceTest {

    @Autowired
    private TranslateAIService translateAIService;

    @Test
    void callApi() {

        String respuesta = translateAIService.callApi("hell is a place on earth");
        log.info("Respuesta: {}", respuesta);
    }
}