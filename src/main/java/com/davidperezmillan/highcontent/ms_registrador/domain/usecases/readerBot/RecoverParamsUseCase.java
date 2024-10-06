package com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;

import java.util.List;

public interface RecoverParamsUseCase {

    List<Param> recoverParams();

    Param recoverParamById(String ky);
}
