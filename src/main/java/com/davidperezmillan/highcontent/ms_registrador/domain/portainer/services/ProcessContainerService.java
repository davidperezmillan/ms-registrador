package com.davidperezmillan.highcontent.ms_registrador.domain.portainer.services;

import com.davidperezmillan.highcontent.ms_registrador.application.portainer.PortainerPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.usecases.ProccessContainerUseCase;
import org.springframework.stereotype.Service;

@Service
public class ProcessContainerService implements ProccessContainerUseCase {

    private final PortainerPort portainerPort;

    public ProcessContainerService(PortainerPort portainerPort) {
        this.portainerPort = portainerPort;
    }

    @Override
    public void processContainer() {
        Container[] containers = portainerPort.getContainer();
        for (Container container : containers) {
            portainerPort.startContainer(container.getId());
        }
    }
}
