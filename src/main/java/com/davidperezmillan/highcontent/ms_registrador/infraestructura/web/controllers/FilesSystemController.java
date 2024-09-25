package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers;


import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.DeleteVideosToPathUseCase;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.ReadVideosToPathUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.VideoResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers.VideoResponseMapper;
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

    @Autowired
    public FilesSystemController(ReadVideosToPathUseCase readVideosToPathUseCase, DeleteVideosToPathUseCase deleteVideosToPathUseCase) {
        this.readVideosToPathUseCase = readVideosToPathUseCase;
        this.deleteVideosToPathUseCase = deleteVideosToPathUseCase;
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
