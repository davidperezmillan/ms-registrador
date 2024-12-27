package com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.config.RestTemplateConfig;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.models.TranslateResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.services.TranslateAIService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = { TranslateAIService.class, RestTemplateConfig.class })
class TranslateAIServiceTest {

    @Autowired
    private TranslateAIService translateAIService;

    @Test
    void callApi() {

        TranslateResponse[] respuesta = translateAIService.callApi("hell is a place on earth");
        log.info("Respuesta: {}", respuesta[0].getTranslationText());
    }
}