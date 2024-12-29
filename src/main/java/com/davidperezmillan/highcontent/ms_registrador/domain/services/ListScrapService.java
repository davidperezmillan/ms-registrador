package com.davidperezmillan.highcontent.ms_registrador.domain.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.ScrapPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.GetListScrapUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ListScrapService implements GetListScrapUseCase {

   private final ScrapPort scrapPort;

    public ListScrapService(ScrapPort scrapPort) {
        this.scrapPort = scrapPort;
    }

    @Override
    public Scene[] getScenes() {
        Scene[] scenes = scrapPort.allScrap();
        for (Scene scene : scenes) {
            log.info("Scene: {}", scene);
        }
        return scenes;
    }

}
