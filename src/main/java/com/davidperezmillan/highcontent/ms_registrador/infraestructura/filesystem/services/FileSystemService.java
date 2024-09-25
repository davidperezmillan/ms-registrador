package com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.services;


import com.davidperezmillan.highcontent.ms_registrador.application.ports.FileSystemPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.dtos.VideoResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.mappers.VideoResponseMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


@Service
@Log4j2
public class FileSystemService implements FileSystemPort {

    @Value("${fileSystem.path}")
    private String directoryPath;


    // Extensiones comunes de videos
    private static final String[] VIDEO_EXTENSIONS = {".mp4", ".avi", ".mkv", ".mov", ".flv", ".wmv"};


    /**
     * Metodo que recorre un directorio y devuelve el listado de ficheros
     * <p>
     * <p>
     * return Listado de objetos de ficheros
     */
    public List<VideoFile> getFilesFromPath() throws IOException {
        List<VideoFile> fileList = new ArrayList<>();
        Path path = Paths.get(directoryPath);

        if (Files.exists(path) && Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (Files.isRegularFile(file)) {
                        log.info("Archivo añadido: {}", file);
                        fileList.add(VideoResponseMapper.map(mapVideoFile(file, attrs)));
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            throw new IllegalArgumentException("El directorio no existe o no es un directorio válido: " + directoryPath);
        }

        return fileList;
    }

    /**
     * Metodo que recupera todos los videos de la carpeta
     * <p>
     *
     * @throws IOException return lista de videos
     */
    public List<VideoFile> getVideoFilesFromPath() throws IOException {
        List<VideoFile> videoFiles = new ArrayList<>();
        Path path = Paths.get(directoryPath);

        if (Files.exists(path) && Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (Files.isRegularFile(file) && isVideoFile(file)) {
                        log.info("Archivo añadido: {}", file);
                        videoFiles.add(VideoResponseMapper.map(mapVideoFile(file, attrs)));
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            throw new IllegalArgumentException("El directorio no existe o no es un directorio válido: " + directoryPath);
        }

        return videoFiles;
    }

    private static boolean isVideoFile(Path file) {
        String fileName = file.getFileName().toString().toLowerCase();
        for (String extension : VIDEO_EXTENSIONS) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private static VideoResponse mapVideoFile(Path file, BasicFileAttributes attrs) {
        VideoResponse video = new VideoResponse();
        video.setFileName(file.getFileName().toString());
        video.setPath(file.toString());
        video.setCreationDate(attrs.creationTime().toString());
        /*
        video.setLastAccessTime(attrs.lastAccessTime().toString());
        video.setLastModifiedTime(attrs.lastModifiedTime().toString());
         */
        try {
            video.setSize(Files.size(file));
        } catch (IOException e) {
            log.warn("Error al recuperar el tamaño del fichero: " + file);
        }
        return video;
    }

}
