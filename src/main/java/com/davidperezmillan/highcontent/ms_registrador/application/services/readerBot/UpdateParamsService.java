package com.davidperezmillan.highcontent.ms_registrador.application.services.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ParamsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.UpdateParamsUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateParamsService implements UpdateParamsUseCase {

    private final ParamsPort paramsPort;

    public UpdateParamsService(ParamsPort paramsPort) {
        this.paramsPort = paramsPort;
    }

    @Override
    public Param updateParam(Param param) {
        return paramsPort.updateParam(param);
    }
}
