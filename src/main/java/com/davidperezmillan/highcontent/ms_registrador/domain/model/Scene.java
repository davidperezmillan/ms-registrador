package com.davidperezmillan.highcontent.ms_registrador.domain.model;

import lombok.Data;

@Data
public class Scene {

    private String id;
    private String title;
    private String description;
    private String translationText;

    private String trailer;

    private String image;
    private Posters posters;

    private Tags[] tags;
}
