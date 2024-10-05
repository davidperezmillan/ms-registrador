package com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ParamsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.mappers.ParamMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.repositories.ParamsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamsService implements ParamsPort {

    private final ParamsRepository paramsRepository;

    public ParamsService(ParamsRepository paramsRepository) {
        this.paramsRepository = paramsRepository;
    }

    @Override
    public List<Param> getAllParams() {
        return ParamMapper.map(paramsRepository.findAll());
    }

    @Override
    public Param getParam(Param param) {
        return ParamMapper.map(
                paramsRepository.findByKey(param.getKey()).orElseThrow(() -> new RuntimeException("Param not found")
                )
        );
    }
}
