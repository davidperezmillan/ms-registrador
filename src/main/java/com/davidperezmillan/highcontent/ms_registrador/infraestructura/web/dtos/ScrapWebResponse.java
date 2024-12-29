package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos;

import lombok.Data;

@Data
public class ScrapWebResponse {

    private String title;
    private String description;
    private String translationText;

    private String posters;

}
