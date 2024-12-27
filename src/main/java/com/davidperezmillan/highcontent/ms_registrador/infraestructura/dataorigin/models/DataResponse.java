package com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models;


import lombok.Data;

@Data
public class DataResponse {

    private String id;
    private String title;
    private String description;
    private String trailer;

    private String image;
    private Posters posters;

    private Tags[] tags;
}
