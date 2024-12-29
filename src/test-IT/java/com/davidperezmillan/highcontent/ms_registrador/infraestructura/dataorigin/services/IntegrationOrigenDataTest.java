package com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.services;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.config.RestTemplateConfig;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models.SceneResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.models.TranslateResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.services.TranslateAIService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest(classes = { TranslateAIService.class, ThePorndbService.class, RestTemplateConfig.class })
class IntegrationOrigenDataTest {

    @Autowired
    private ThePorndbService thePorndbService;

    @Autowired
    private TranslateAIService translateAIService;

    @Test
    void callApi() {
        log.info("Test ThePorndbService");
        SceneResponse thePorndbResponse = thePorndbService.callApi();
        for (int i = 0; i < 5; i++) {
            TranslateResponse[] respuesta = translateAIService.callApi(thePorndbResponse.getData()[i].getDescription());
            log.info("titulo {}\n {}", thePorndbResponse.getData()[i].getTitle(),respuesta[0].getTranslationText());
        }



    }
}