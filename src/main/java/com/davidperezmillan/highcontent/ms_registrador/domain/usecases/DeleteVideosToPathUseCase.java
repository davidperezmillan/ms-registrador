package com.davidperezmillan.highcontent.ms_registrador.domain.usecases;

import java.io.IOException;

public interface DeleteVideosToPathUseCase {

    void deleteVideosToPath(String name) throws IOException;
}
