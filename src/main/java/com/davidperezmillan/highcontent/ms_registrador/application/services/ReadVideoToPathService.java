package com.davidperezmillan.highcontent.ms_registrador.application.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.FileSystemPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.ReadVideosToPathUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReadVideoToPathService implements ReadVideosToPathUseCase {

    private final FileSystemPort fileSystemPort;

    @Autowired
    public ReadVideoToPathService(FileSystemPort fileSystemPort) {
        this.fileSystemPort = fileSystemPort;
    }

    @Override
    public List<VideoFile> readVideosToPath() throws IOException {
        List<VideoFile> videos = fileSystemPort.getVideoFilesFromPath();

        // ordenar por fecha de creacion
        videos.sort((v1, v2) -> v1.getCreationDate().compareTo(v2.getCreationDate()));

        return videos;
    }
}
