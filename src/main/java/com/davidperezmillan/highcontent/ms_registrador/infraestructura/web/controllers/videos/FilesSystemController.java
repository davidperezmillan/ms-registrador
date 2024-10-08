package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers.videos;


import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.videos.DeleteVideosToPathUseCase;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.videos.GenerateVideoUseCase;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.videos.ReadVideosToPathUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.videos.VideoResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers.videos.VideoWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files") // Añade el prefijo /files a todos los endpoinds de este controlador
public class FilesSystemController {

    private final ReadVideosToPathUseCase readVideosToPathUseCase;
    private final DeleteVideosToPathUseCase deleteVideosToPathUseCase;
    private final GenerateVideoUseCase generateVideosToPathUseCase;

    @Autowired
    public FilesSystemController(ReadVideosToPathUseCase readVideosToPathUseCase,
                                 DeleteVideosToPathUseCase deleteVideosToPathUseCase,
                                 GenerateVideoUseCase generateVideosToPathUseCase) {
        this.readVideosToPathUseCase = readVideosToPathUseCase;
        this.deleteVideosToPathUseCase = deleteVideosToPathUseCase;
        this.generateVideosToPathUseCase = generateVideosToPathUseCase;
    }

    /*
    @GetMapping("/files")
    public ResponseEntity<List<Path>> getFiles() {
        try {
            return ResponseEntity.ok(fileSystemService.getFilesFromPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    */

    
    @GetMapping("/videos")
    public ResponseEntity<List<VideoResponse>> getVideos() {
        try {
            return ResponseEntity.ok(VideoWebMapper.map(readVideosToPathUseCase.readVideosToPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/videos/generate/{name}")
    public ResponseEntity<Void> generateVideos(@PathVariable String name) {
        try {
            generateVideosToPathUseCase.generateImagesFromVideo(name);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/videos/{name}")
    public ResponseEntity<Void> deleteVideos(@PathVariable String name) {
        try {
            deleteVideosToPathUseCase.deleteVideosToPath(name);
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
