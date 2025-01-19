package com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.services;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.config.RestTemplateConfig;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.models.Container;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.models.StatusEnum;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest(classes = { PortainerService.class, RestTemplateConfig.class })
class PortainerServiceTest {

    @Autowired
    private PortainerService portainerService;

    @Test
    void getContainersExited() {
        List<Container> response = portainerService.getContainersExited(StatusEnum.EXITED);
        for (Container container : response) {
            log.info("Container: {} : id {}", container.getNames().get(0), container.getId());
        }

    }

    @Test
    void startContainer() {
        portainerService.startContainer("5fd70b39d5ecc39ef19d59181ac2aad9bc9e87d808a26d9676b3bdc6d1abc10b");
    }
}