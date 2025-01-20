package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.portainer.models;

import lombok.Data;

@Data
public class ContainerResponse {

    private String id;
    private String names;
    private String state;

}
