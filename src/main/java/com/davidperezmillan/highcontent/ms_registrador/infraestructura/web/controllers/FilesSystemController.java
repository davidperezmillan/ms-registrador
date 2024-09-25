package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers;


import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.ReadVideosToPathUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.VideoResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers.VideoResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files") // Añade el prefijo /files a todos los endpoinds de este controlador
public class FilesSystemController {

    private final ReadVideosToPathUseCase readVideosToPathUseCase;

    @Autowired
    public FilesSystemController(ReadVideosToPathUseCase readVideosToPathUseCase) {
        this.readVideosToPathUseCase = readVideosToPathUseCase;
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
            return ResponseEntity.ok(VideoResponseMapper.map(readVideosToPathUseCase.readVideosToPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
