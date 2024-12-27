package com.davidperezmillan.highcontent.ms_registrador.domain.usecases;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;

public interface GetListScenesUseCase {

    Scene[] getScenes(int nRegistros);
}
