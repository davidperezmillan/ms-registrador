package com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.services;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@SpringBootTest(classes = { CerScrapService.class})
class CerScrapServiceTest {

    @Autowired
    private CerScrapService cerScrapService;

    @Test
    void testAllScrap() {
        Scene[] result = cerScrapService.allScrap();
        for (Scene s : result) {
            log.info("Result: {}", s);
        }
        assertNotNull(result, "The result should not be null");
    }


}