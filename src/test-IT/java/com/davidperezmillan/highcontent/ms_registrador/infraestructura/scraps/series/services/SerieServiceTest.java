package com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.series.services;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.series.models.SerieResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest(classes = { SerieService.class})
class SerieServiceTest {

    @Autowired
    private SerieService serieService;

    @Test
    void allSeries() {
        List<SerieResponse> listado = serieService.allSeries();
        for (SerieResponse serie : listado){
            log.info(serie);
        }
    }

}