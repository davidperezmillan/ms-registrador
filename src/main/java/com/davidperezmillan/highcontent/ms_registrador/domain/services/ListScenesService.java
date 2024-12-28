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
    public Scene[] getScenes() {
        Scene[] scenes = dataOriginPort.getAllScenes();

        //randomiza el array
        //scenes = randomize(scenes);

        //reduce el array a 3 elementos
        //scenes = reduce(scenes, nRegistros);

        //traduce la descripcion de las escenas
        //for (Scene scene : scenes) {
        //    scene.setTranslationText(translatePort.translate(scene.getDescription()));
        //}
        return scenes;
    }

//    private Scene[] randomize(Scene[] scenes) {
//        // Convert the array to a list
//        List<Scene> sceneList = Arrays.asList(scenes);
//        // Shuffle the list
//        Collections.shuffle(sceneList);
//        // Convert the list back to an array
//        return sceneList.toArray(new Scene[0]);
//    }
//
//    private Scene[] reduce(Scene[] scenes, int nRegistros) {
//        if (scenes.length > nRegistros) {
//            scenes = Arrays.copyOf(scenes, nRegistros);
//        }
//        return scenes;
//    }
}
