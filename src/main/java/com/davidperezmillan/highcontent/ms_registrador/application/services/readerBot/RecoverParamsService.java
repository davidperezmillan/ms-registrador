package com.davidperezmillan.highcontent.ms_registrador.application.services.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ParamsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.RecoverParamsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoverParamsService implements RecoverParamsUseCase {

    private final ParamsPort paramsPort;

    @Autowired
    public RecoverParamsService(ParamsPort paramsPort) {
        this.paramsPort = paramsPort;
    }

    @Override
    public List<Param> recoverParams() {
        return paramsPort.getAllParams();
    }

    @Override
    public Param recoverParamById(String ky) {
        Param param = new Param();
        param.setKy(ky);
        return paramsPort.getParam(param);
    }
}
