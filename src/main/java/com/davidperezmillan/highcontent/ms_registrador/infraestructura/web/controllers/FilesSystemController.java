package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.services.FileSystemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/files") // Añade el prefijo /files a todos los endpoinds de este controlador
public class FilesSystemController {

    private FileSystemService fileSystemService;

    public FilesSystemController(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    @GetMapping("/files")
    public ResponseEntity<List<Path>> getFiles() {
        try {
            return ResponseEntity.ok(fileSystemService.getFilesFromPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @GetMapping("/videos")
    public ResponseEntity<List<Path>> getVideos() {
        try {
            return ResponseEntity.ok(fileSystemService.getVideoFilesFromPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
