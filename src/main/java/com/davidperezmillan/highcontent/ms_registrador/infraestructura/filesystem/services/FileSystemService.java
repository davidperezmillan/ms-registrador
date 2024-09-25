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

        // voy a probar a extraer imagenes del ultimo video
        try {
            if (!videoFiles.isEmpty()) {
                extractImages(videoFiles.get(videoFiles.size() - 1).getPath(), directoryPath, 5);
            }
        } catch (Exception e) {
            log.error("Error al extraer imagenes del video: " + videoFiles.get(videoFiles.size() - 1).getPath(), e);
            throw new RuntimeException(e);
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


    private void extractImages(String inputVideoPath, String outputImagePath, int numImages) throws Exception {
        // Crear un FrameGrabber para leer el archivo de video
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(inputVideoPath);
        frameGrabber.start();

        // Obtener la duración total del video y el número total de frames
        int totalFrames = frameGrabber.getLengthInFrames();
        int frameRate = (int) frameGrabber.getFrameRate();
        int totalDurationInSeconds = (totalFrames / frameRate);

        // Calcular los intervalos en los que se extraerán las imágenes
        int interval = totalDurationInSeconds / numImages;

        // Convertidor de Frame a BufferedImage
        Java2DFrameConverter converter = new Java2DFrameConverter();

        for (int i = 0; i < numImages; i++) {
            // Calcular el timestamp en segundos para cada imagen
            int timestamp = i * interval;

            // Mover al frame correspondiente según el timestamp
            frameGrabber.setTimestamp(timestamp * 1000000L);  // Conversión a microsegundos
            Frame frame = frameGrabber.grabImage();  // Extraer el frame

            if (frame != null) {
                // Convertir el frame a BufferedImage
                BufferedImage bufferedImage = converter.convert(frame);

                // Guardar la imagen como archivo PNG
                ImageIO.write(bufferedImage, "png", new File(outputImagePath + i + ".png"));

                System.out.println("Imagen " + i + " guardada en: " + outputImagePath + i + ".png");
            }
        }

        frameGrabber.stop();
    }
}
