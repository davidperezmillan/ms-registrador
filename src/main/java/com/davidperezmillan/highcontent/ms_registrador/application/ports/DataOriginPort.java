package com.davidperezmillan.highcontent.ms_registrador.application.ports;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.MovieTypeEnum;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;

public interface DataOriginPort {


    Scene[] getAllScenes(MovieTypeEnum movieTypeEnum);

    Scene[] getScenesByTitle(MovieTypeEnum movieTypeEnum,String title);
}
