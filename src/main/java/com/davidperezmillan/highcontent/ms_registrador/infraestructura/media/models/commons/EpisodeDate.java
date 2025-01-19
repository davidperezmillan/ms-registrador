package com.davidperezmillan.highcontent.ms_registrador.infraestructura.media.models.commons;

import lombok.Data;

@Data
public class EpisodeDate {
    private long unix;
    private String text;
    private boolean reached;
    private String hour;
    private String nowDiff;
}