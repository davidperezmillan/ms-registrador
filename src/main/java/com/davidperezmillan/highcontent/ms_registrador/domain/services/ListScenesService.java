package com.davidperezmillan.highcontent.ms_registrador.domain.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.DataOriginPort;
import com.davidperezmillan.highcontent.ms_registrador.application.ports.TranslatePort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.GetListScenesUseCase;
import org.springframework.stereotype.Service;

@Service
public class ListScenesService implements GetListScenesUseCase {

   private final DataOriginPort dataOriginPort;
   private final TranslatePort translatePort;

    public ListScenesService(DataOriginPort dataOriginPort,
                             TranslatePort translatePort) {
        this.dataOriginPort = dataOriginPort;
        this.translatePort = translatePort;
    }

    @Override
    public Scene[] getScenes(int nRegistros) {
        Scene[] scenes = dataOriginPort.getAllScenes();

        // ordenar de forma aleatoria
        for (int i = 0; i < scenes.length; i++) {
            int randomIndexToSwap = (int) (Math.random() * scenes.length);
            Scene temp = scenes[randomIndexToSwap];
            scenes[randomIndexToSwap] = scenes[i];
            scenes[i] = temp;
        }

        //reduce el array a 3 elementos
        if (scenes.length > nRegistros) {
            Scene[] scenesReduced = new Scene[nRegistros];
            for (int i = 0; i < nRegistros; i++) {
                scenesReduced[i] = scenes[i];
            }
            scenes = scenesReduced;
        }
        for (Scene scene : scenes) {
            scene.setTranslationText(translatePort.translate(scene.getDescription()));
        }
        return scenes;
    }
}
