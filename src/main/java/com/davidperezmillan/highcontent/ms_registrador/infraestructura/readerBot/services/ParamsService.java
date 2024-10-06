package com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ParamsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.mappers.ParamMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.models.ParamsModel;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.repositories.ParamsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ParamsService implements ParamsPort {

    private final ParamsRepository paramsRepository;

    public ParamsService(ParamsRepository paramsRepository) {
        this.paramsRepository = paramsRepository;
    }

    @Override
    public List<Param> getAllParams() {
        List<ParamsModel> lista = paramsRepository.findAll();
        for (ParamsModel p : lista) {
            log.info(p);
        }
        return ParamMapper.map(paramsRepository.findAll());
    }

    @Override
    public Param getParam(Param param) {
        return ParamMapper.map(
                paramsRepository.findByky(param.getKy()).orElseThrow(() -> new RuntimeException("Param not found")
                )
        );
    }

    @Override
    public Param updateParam(Param param) {
        log.info("Updating param: {}", param);
        return ParamMapper.map(
                paramsRepository.saveAndFlush(ParamMapper.map(param))
        );
    }
}
