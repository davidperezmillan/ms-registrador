package com.davidperezmillan.highcontent.ms_registrador.application.services.videos;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.videos.FileSystemPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.videos.DeleteVideosToPathUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeleteVideosToPathService implements DeleteVideosToPathUseCase {

    private final FileSystemPort fileSystemPort;

    @Autowired
    public DeleteVideosToPathService(FileSystemPort fileSystemPort) {
        this.fileSystemPort = fileSystemPort;
    }

    @Override
    public void deleteVideosToPath(String name) throws IOException {
        fileSystemPort.deleteFilesToPath(name);

    }
}
