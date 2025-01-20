package com.davidperezmillan.highcontent.ms_registrador.domain.portainer.services;

import com.davidperezmillan.highcontent.ms_registrador.application.portainer.PortainerPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.usecases.GetContainerUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class GetContainerService implements GetContainerUseCase {


    private final PortainerPort portainerPort;

    public GetContainerService(PortainerPort portainerPort) {
        this.portainerPort = portainerPort;
    }

    @Override
    public Container[] getContainer() {
        return portainerPort.getContainer();
    }

    @Override
    public Container[] getContainerStop() {
        return portainerPort.getContainerStop();
    }
}
