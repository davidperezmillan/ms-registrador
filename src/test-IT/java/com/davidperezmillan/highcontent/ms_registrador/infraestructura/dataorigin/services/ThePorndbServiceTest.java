package com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.services;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.config.RestTemplateConfig;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models.DataResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models.SceneResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest(classes = { ThePorndbService.class, RestTemplateConfig.class })
class ThePorndbServiceTest {

    @Autowired
    private ThePorndbService thePorndbService;

    @Test
    void callApi() {
        String url = "https://api.theporndb.net/scenes";
        SceneResponse sceneResponse = thePorndbService.callApi(url);
        for (DataResponse data : sceneResponse.getData()) {
            log.info("titulo: {}, descripcion: {}", data.getTitle(), data.getDescription());
            log.info("trailers: {}", data.getTrailer());
            log.info("imagenes: {}", data.getPosters().getLarge());
        }


    }
}