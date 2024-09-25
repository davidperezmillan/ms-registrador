package com.davidperezmillan.highcontent.ms_registrador.application.ports;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;

import java.io.IOException;
import java.util.List;

public interface FileSystemPort {

    List<VideoFile> getFilesFromPath() throws IOException;

    List<VideoFile> getVideoFilesFromPath() throws IOException;

}
