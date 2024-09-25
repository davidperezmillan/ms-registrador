package com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.services;


import com.davidperezmillan.highcontent.ms_registrador.application.ports.FileSystemPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.dtos.VideoResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.mappers.VideoResponseMapper;
import lombok.extern.log4j.Log4j2;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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

        // quiero sacar 5 imagenes del ultimo video, crear una carpeta de images y guardarlas
        // crear una carpeta dentro de la carpeta de videos
        // extraer las imagenes
        // guardarlas en la carpeta creada
        // devolver la lista de videos
        if (!videoFiles.isEmpty()) {
            VideoFile lastVideo = videoFiles.get(videoFiles.size() - 1);
            String outputFolder = directoryPath + "/images";
            extractFrames(lastVideo.getPath(), outputFolder, 5);
        }


        return videoFiles;
    }

    @Override
    public void deleteFilesToPath(String name) throws IOException {
        Path path = Paths.get(directoryPath + "/" + name);
        if (Files.exists(path)) {
            Files.delete(path);
        } else {
            throw new IllegalArgumentException("El fichero no existe: " + path);
        }

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
        video.setExtension(file.getFileName().toString().substring(file.getFileName().toString().lastIndexOf(".")));
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

    /**
     * Metodo para extraer n images a lo largo del video
     *
     * @param videoPath video a extraer las imagenes
     * @param outputFolder carpeta de salida
     * @param nFrames numero de imagenes a extraer
     * return
     *
     */
    private void extractFrames(String videoPath, String outputFolder, int nFrames) {
        File outputDir = new File(outputFolder);
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // Crear la carpeta de salida si no existe
        }
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath)) {
            grabber.start();
            int totalFrames = grabber.getLengthInFrames();
            double frameInterval = (double) totalFrames / nFrames; // Calcular el intervalo de fotogramas

            Java2DFrameConverter converter = new Java2DFrameConverter();
            for (int i = 0; i < nFrames; i++) {
                grabber.setFrameNumber((int) (i * frameInterval)); // Establecer el fotograma a extraer
                Frame frame = grabber.grabImage(); // Capturar el fotograma

                if (frame != null) {
                    BufferedImage image = converter.convert(frame); // Convertir a BufferedImage
                    String outputFilePath = String.format("%s/frame_%04d.png", outputFolder, i + 1);
                    ImageIO.write(image, "png", new File(outputFilePath)); // Guardar la imagen
                }
            }

            grabber.stop();
            System.out.println("Frames extracted successfully to: " + outputFolder);
        } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
