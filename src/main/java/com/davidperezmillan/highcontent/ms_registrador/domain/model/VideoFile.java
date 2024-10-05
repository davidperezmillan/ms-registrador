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

}
