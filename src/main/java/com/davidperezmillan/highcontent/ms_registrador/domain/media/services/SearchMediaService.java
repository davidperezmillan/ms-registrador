package com.davidperezmillan.highcontent.ms_registrador.domain.media.services;

import com.davidperezmillan.highcontent.ms_registrador.application.media.ports.MediaPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.media.models.Media;
import com.davidperezmillan.highcontent.ms_registrador.domain.media.usecases.SearchMediaUseCase;
import org.springframework.stereotype.Service;

@Service
public class SearchMediaService implements SearchMediaUseCase {

    private final MediaPort mediaPort;

    public SearchMediaService(MediaPort mediaPort) {
        this.mediaPort = mediaPort;
    }


    @Override
    public Media[] search(String title) {
        Media[] search = mediaPort.search(title);
        for (Media media : search) {
            Media info = mediaPort.getFicha(media.getId());
            media.setSinopsis(info.getSinopsis());
        }
        return search;
    }
}
