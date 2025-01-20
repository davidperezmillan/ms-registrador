package com.davidperezmillan.highcontent.ms_registrador.infraestructura.media.models.commons;

import lombok.Data;

@Data
public class Fichas {
    private int id;
    private String title;
    private String poster;
    private String posterColor;
    private String cover;
    private int rating;
    private int votes;
    private boolean isSerie;
    private String type;
    private int year;
    private User user;
}
