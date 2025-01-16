package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons;

import lombok.Data;

import java.util.List;

@Data
public class EpisodeGroup {
    private int season;
    private List<Episode> episodesList;
}