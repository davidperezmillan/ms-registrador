package com.davidperezmillan.highcontent.ms_registrador.application.ports;

import java.io.IOException;

public interface VideoPort {


    void extractFrameFromVideo(String videoPath, String outputImagePath, int time) throws InterruptedException, IOException;

    void generateMosaic(String videoPath, String outputMosaicPath, int numRows, int numCols) throws IOException, InterruptedException;
}
