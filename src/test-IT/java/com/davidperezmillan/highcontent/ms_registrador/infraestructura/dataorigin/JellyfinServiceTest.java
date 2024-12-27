package com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.player.models.UserDtos;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.player.services.JellyfinService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest(classes = { JellyfinService.class, RestTemplate.class })
class JellyfinServiceTest {


    @Autowired
    private JellyfinService jellyfinService;

    @Test
    void callApi() {

        UserDtos[] respuesta = jellyfinService.getUser();
        for (UserDtos userDtos : respuesta) {
            log.info("User: {}", userDtos);
        }
    }
}