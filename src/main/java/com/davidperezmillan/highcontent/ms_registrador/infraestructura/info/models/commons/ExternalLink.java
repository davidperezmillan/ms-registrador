package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons;

import lombok.Data;

@Data
public class ExternalLink {
    private String name;
    private String externalId;
    private String externalIdExtra;
    private String link;
}