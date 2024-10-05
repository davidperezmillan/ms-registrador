package com.davidperezmillan.highcontent.ms_registrador.infraestructura.videos.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.videos.VideoPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

@Service
@Log4j2
public class FFmpegService implements VideoPort {


    @Override
    public void extractFrameFromVideo(String videoPath, String outputImagePath, int time) throws InterruptedException, IOException {
        log.info("Extrayendo frame del video: {}, a la carpeta {}", videoPath, outputImagePath);
        // creamos el nombre del archivo de salida con el nombre del video
        String outputImageName = outputImagePath + "/" + new File(videoPath).getName() + ".png";

        // Comando para extraer un frame del video
        log.info("Extrayendo frame del video: {}, a {}", videoPath, outputImageName);
        String extractFrameCmd = String.format(
                "ffmpeg -i \"%s\" -ss %d -vframes 1 \"%s\"",
                videoPath, time, outputImageName
        );

        // Ejecutar el comando para extraer el frame
        try {
            runCommand(extractFrameCmd);
        } catch (IOException | InterruptedException e) {
            log.error("Error al extraer el frame del video: {}", videoPath);
            throw e;
        }

        log.info("Frame {} extraído en: {}", outputImageName, outputImagePath);
    }

    @Override
    public void generateMosaic(String videoPath, String outputMosaicPath, int numRows, int numCols) throws IOException, InterruptedException {

        // recuperar el nombre del video sin extension
        String outputImageName = outputMosaicPath + "/" + new File(videoPath).getName() + ".png";

        // Comando para extraer frames del video
        String extractFramesCmd = String.format(
                "ffmpeg -i \"%s\" -vf \"fps=1,scale=320:240\" /tmp/frame_%%03d.png",
                videoPath
        );

        // Comando para crear el mosaico de imágenes
        String createMosaicCmd = String.format(
                "ffmpeg -i /tmp/frame_%%03d.png -filter_complex \"tile=%dx%d\" \"%s\"",
                numCols, numRows, outputImageName
        );

        // Ejecutar el comando para extraer frames
        runCommand(extractFramesCmd);

        // Ejecutar el comando para crear el mosaico
        runCommand(createMosaicCmd);

        log.info("Mosaico {} generado en: {}", outputImageName, outputMosaicPath);
    }

    private void runCommand(String command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), log::info);
        Executors.newSingleThreadExecutor().submit(streamGobbler);

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Error ejecutando el comando: " + command);
        }
    }

}
