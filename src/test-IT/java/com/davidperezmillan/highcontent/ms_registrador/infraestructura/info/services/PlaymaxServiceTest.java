package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.services;

import com.davidperezmillan.highcontent.ms_registrador.domain.media.models.Media;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest(classes = { PlaymaxService.class, RestTemplate.class})
class PlaymaxServiceTest {

    @Autowired
    private PlaymaxService playmaxService;

    @Test
    void when_search_ok() {
        assertDoesNotThrow(() -> {
            Media[] result = playmaxService.search("Ashoka");
            log.info("Result: {}", result);
            assertNotNull(result, "The result should not be null");
        });
    }
    @Test
    void when_search_ko() {
        assertThrows(HttpClientErrorException.class, () -> playmaxService.search(null));
    }

    @Test
    void when_ficha_ok(){
        assertDoesNotThrow(() -> {
            Media result = playmaxService.getFicha(135591);
            log.info("Result: {}", result);
            assertNotNull(result, "The result should not be null");
        });
    }
}