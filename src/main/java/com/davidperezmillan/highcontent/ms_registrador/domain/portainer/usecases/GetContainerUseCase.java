package com.davidperezmillan.highcontent.ms_registrador.domain.portainer.usecases;

import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;

public interface GetContainerUseCase {

    Container[] getContainer();

    Container[] getContainerStop();

}
