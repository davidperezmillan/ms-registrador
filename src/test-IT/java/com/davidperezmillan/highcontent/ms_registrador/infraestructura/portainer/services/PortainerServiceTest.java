package com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.services;

import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.config.RestTemplateConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest(classes = { PortainerService.class, RestTemplateConfig.class })
class PortainerServiceTest {

    @Autowired
    private PortainerService portainerService;

    @Test
    void getContainersExited() {
        Container[] response = portainerService.getContainer();
        for (Container containerPortainerResponse : response) {
            log.info("Container: {} : id {}", containerPortainerResponse.getNames(), containerPortainerResponse.getId());
        }

    }

    @Test
    void startContainer() {
        portainerService.startContainer("8cea021433c9f121d4c82d2c15d0b44298e14644662d48f5e8012b422ca51238");
    }
}