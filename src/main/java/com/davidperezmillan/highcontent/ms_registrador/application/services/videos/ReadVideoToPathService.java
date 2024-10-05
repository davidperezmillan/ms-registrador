package com.davidperezmillan.highcontent.ms_registrador.application.services.videos;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.videos.FileSystemPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.videos.ReadVideosToPathUseCase;
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

        // ordenar por fecha de creacion descendente
        videos.sort((v1, v2) -> v2.getCreationDate().compareTo(v1.getCreationDate()));

        return videos;
    }
}
