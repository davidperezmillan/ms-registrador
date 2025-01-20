package com.davidperezmillan.highcontent.ms_registrador.domain.portainer.services;

import com.davidperezmillan.highcontent.ms_registrador.application.portainer.PortainerPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.usecases.StartContainerUseCase;
import org.springframework.stereotype.Service;

@Service
public class StartContainerService implements StartContainerUseCase {

    private final PortainerPort portainerPort;

    public StartContainerService(PortainerPort portainerPort) {
        this.portainerPort = portainerPort;
    }

    @Override
    public void startContainer(String containerId) {
        portainerPort.startContainer(containerId);
    }
}
