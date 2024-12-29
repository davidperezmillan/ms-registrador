package com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.models.CerScrapResponse;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

public class CerScrapMapper {

    public static Scene map(CerScrapResponse source) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CerScrapResponse.class, Scene.class);

        return modelMapper.map(source, Scene.class);
    }

    public static Scene[] map(CerScrapResponse[] source) {
        return Arrays.stream(source)
                .map(CerScrapMapper::map)
                .toArray(Scene[]::new);
    }
}
