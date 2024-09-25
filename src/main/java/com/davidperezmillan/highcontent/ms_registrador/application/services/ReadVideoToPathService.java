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
        return fileSystemPort.getVideoFilesFromPath();
    }
}
