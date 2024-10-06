package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.readerBot.ParamRequest;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.readerBot.ParamResponse;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface ParamWebMapper {

    static List<ParamResponse> map(List<Param> params) {
        return params.stream()
                .map(ParamWebMapper::mapToResponse)
                .toList();
    }

    static Param map(ParamRequest paramRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paramRequest, Param.class);
    }

    static ParamRequest map(Param param) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(param, ParamRequest.class);
    }

    static Param map(ParamResponse paramResponse) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paramResponse, Param.class);
    }

    static ParamResponse mapToResponse(Param param) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(param, ParamResponse.class);
    }


}
