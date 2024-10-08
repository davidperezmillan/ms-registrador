package com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.dtos;

import lombok.Data;

@Data
public class VideoResponse {

    private String fileName;
    private String path;
    private String extension;
    private long size;
    private String creationDate;

}
