package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    private int id;
    private String title;
    private String originalTitle;
    private String poster;
    private String posterColor;
    private String cover;
    private double rating;
    private int votes;
    private boolean isSerie;
    private String type;
    private String status;
    private int seasons;
    private int episodes;
    private int year;
    private int duration;
    private String durationText;
    private String country;
    private int countryId;
    private List<ExternalLink> externalLinks;
    private List<CrewGroup> crew;
    private List<Genre> genres;
    private List<Topic> topics;
    private String sinopsis;
    private List<TvBroadcast> tvBroadcasts;
    private List<EpisodeGroup> listEpisodes;
}