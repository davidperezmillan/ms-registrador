package com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.dataorigin.models.DataResponse;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

public class DataResponseMapper {

    public static Scene map(DataResponse source) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(DataResponse.class, Scene.class);

        return modelMapper.map(source, Scene.class);
    }

    public static Scene[] map(DataResponse[] source) {
        return Arrays.stream(source)
                .map(DataResponseMapper::map)
                .toArray(Scene[]::new);
    }
}
