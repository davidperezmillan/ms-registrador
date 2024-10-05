package com.davidperezmillan.highcontent.ms_registrador.application.services.videos;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.videos.VideoPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.videos.GenerateVideoUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Log4j2
public class GenerateVideoService implements GenerateVideoUseCase {

    @Value("${fileSystem.path}")
    private String directoryPath;

    private final VideoPort videoPort;

    @Autowired
    public GenerateVideoService(VideoPort videoPort) {
        this.videoPort = videoPort;
    }

    @Override
    public void generateImagesFromVideo(String name) throws IOException {

        VideoFile videoFile = new VideoFile();
        videoFile.setPath(directoryPath + "/" + name);
        String lastVideo = videoFile.getPath();
        String outputFolder = directoryPath + "/images";
        if (!Files.exists(Paths.get(directoryPath + "/images"))) {
            Files.createDirectories(Paths.get(directoryPath + "/images"));
        }
        log.info("Procesando: {} a {}", lastVideo, outputFolder);
        // Crear un hilo para generar el mosaico
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                //generator.generateMosaic(lastVideo, outputFolder, 4, 4);
                videoPort.extractFrameFromVideo(lastVideo, outputFolder, 30);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();

    }
}
