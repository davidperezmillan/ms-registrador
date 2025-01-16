package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons;

import lombok.Data;

@Data
public class Episode {
    private int id;
    private int season;
    private int episode;
    private String episodeText;
    private String name;
    private EpisodeDate date;
    private double rating;
    private User user;
    private Before before;
}