package com.davidperezmillan.highcontent.ms_registrador.domain.usecases;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.MovieTypeEnum;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;

public interface GetListScenesUseCase {

    Scene[] getScenes(MovieTypeEnum movieTypeEnum);

    Scene[] getScenesByTitle(MovieTypeEnum movieTypeEnum,String title);
}
