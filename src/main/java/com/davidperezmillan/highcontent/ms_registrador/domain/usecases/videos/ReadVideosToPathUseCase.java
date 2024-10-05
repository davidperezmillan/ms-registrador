package com.davidperezmillan.highcontent.ms_registrador.domain.usecases.videos;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;

import java.io.IOException;
import java.util.List;

public interface ReadVideosToPathUseCase {

    List<VideoFile> readVideosToPath() throws IOException;
}
