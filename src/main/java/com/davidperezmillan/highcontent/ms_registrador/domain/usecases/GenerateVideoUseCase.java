package com.davidperezmillan.highcontent.ms_registrador.domain.usecases;

import java.io.IOException;

public interface GenerateVideoUseCase {

    void generateImagesFromVideo(String name) throws IOException;
}
