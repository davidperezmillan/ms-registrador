package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.portainer.models;

import lombok.Data;

@Data
public class ContainersResponse {

    private ContainerResponse[] containers;
    private int count;

    public ContainersResponse(ContainerResponse[] containersStop) {
        this.containers = containersStop;
        this.count = containersStop.length;
    }
}
