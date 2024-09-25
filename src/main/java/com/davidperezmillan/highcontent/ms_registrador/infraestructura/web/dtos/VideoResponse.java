package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos;

import lombok.Data;

@Data
public class VideoResponse {

    private String fileName;
    private String path;
    private String extension;
    private String size;
    private String creationDate;
}
