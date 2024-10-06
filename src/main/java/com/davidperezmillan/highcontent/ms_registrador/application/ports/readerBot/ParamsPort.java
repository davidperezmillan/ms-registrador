package com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;

import java.util.List;

public interface ParamsPort {

    List<Param> getAllParams();

    Param getParam(Param chat);

    Param updateParam(Param param);
}
