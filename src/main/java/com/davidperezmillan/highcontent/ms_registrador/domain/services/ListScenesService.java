package com.davidperezmillan.highcontent.ms_registrador.domain.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.DataOriginPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.MovieTypeEnum;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.GetListScenesUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ListScenesService implements GetListScenesUseCase {

   private final DataOriginPort dataOriginPort;


    public ListScenesService(DataOriginPort dataOriginPort) {
        this.dataOriginPort = dataOriginPort;

    }

    @Override
    public Scene[] getScenes(MovieTypeEnum movieTypeEnum) {
        Scene[] scenes = dataOriginPort.getAllScenes(movieTypeEnum);
        return scenes;
    }

    @Override
    public Scene[] getScenesByTitle(MovieTypeEnum movieTypeEnum, String title) {
        Scene[] scenes = dataOriginPort.getScenesByTitle(movieTypeEnum, title);
        return scenes;
    }


}
