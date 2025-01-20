package com.davidperezmillan.highcontent.ms_registrador.application.portainer;

import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;

public interface PortainerPort {

    Container[] getContainer();

    Container[] getContainerStop();

    void startContainer(String containerId);
}
