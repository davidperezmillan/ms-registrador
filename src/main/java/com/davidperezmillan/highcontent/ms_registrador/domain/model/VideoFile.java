package com.davidperezmillan.highcontent.ms_registrador.domain.model;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class VideoFile {

    private String fileName;
    private String path;
    private String extension;
    private long size;
    private LocalDateTime creationDate;


    /*
    private String lastModifiedDate;
    private String lastAccessDate;
    private String owner;
    private String group;
    private String permissions;
    private String type;
    private String duration;
    private String resolution;
    private String codec;
    private String bitrate;
    private String framerate;
    private String audioCodec;
    private String audioBitrate;
    */


}
