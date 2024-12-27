package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos;

import lombok.Data;

@Data
public class SceneWebResponse {

    private String id;
    private String title;
    private String description;
    private String translationText;
    private String trailer;

    private String image;
    private String posters;
    private String[] tags;
}
