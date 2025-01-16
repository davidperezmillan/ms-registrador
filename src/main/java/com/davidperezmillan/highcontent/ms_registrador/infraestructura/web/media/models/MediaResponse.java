package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.media.models;

import lombok.Data;

@Data
public class MediaResponse {

    private long id;
    private String title;
    private String poster;
    private String sinopsis;
}
