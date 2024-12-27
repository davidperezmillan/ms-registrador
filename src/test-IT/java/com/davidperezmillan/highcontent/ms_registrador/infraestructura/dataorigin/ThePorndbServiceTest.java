package com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.config.RestTemplateConfig;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models.DataResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models.SceneResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.services.ThePorndbService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = { ThePorndbService.class, RestTemplateConfig.class })
class ThePorndbServiceTest {

    @Autowired
    private ThePorndbService thePorndbService;

    @Test
    void callApi() {
        SceneResponse sceneResponse = thePorndbService.callApi();
        for (DataResponse data : sceneResponse.getData()) {
            log.info("titulo: {}, descripcion: {}", data.getTitle(), data.getDescription());
            log.info("trailers: {}", data.getTrailer());
            log.info("imagenes: {}", data.getPosters().getLarge());
        }


    }
}