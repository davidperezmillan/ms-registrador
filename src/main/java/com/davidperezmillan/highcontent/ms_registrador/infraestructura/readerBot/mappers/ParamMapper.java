package com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.mappers;


import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.models.ParamsModel;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public interface ParamMapper {

    static List<Param> map(List<ParamsModel> params) {
        return params.stream()
                .map(ParamMapper::map)
                .collect(Collectors.toList());
    }

    static Param map(ParamsModel paramsModel) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paramsModel, Param.class);
    }

    static ParamsModel map(Param param) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(param, ParamsModel.class);
    }
}
