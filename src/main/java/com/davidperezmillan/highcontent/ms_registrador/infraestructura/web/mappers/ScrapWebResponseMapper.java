package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.ScrapWebResponse;
import org.modelmapper.ModelMapper;

import java.util.Arrays;


public class ScrapWebResponseMapper {

    public static ScrapWebResponse map(Scene source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Scene.class, ScrapWebResponse.class)
                .addMapping(src -> src.getPosters().getLarge(), ScrapWebResponse::setPosters);

        return modelMapper.map(source, ScrapWebResponse.class);
    }

    public static ScrapWebResponse[] map(Scene[] source) {
        return Arrays.stream(source)
                .map(ScrapWebResponseMapper::map)
                .toArray(ScrapWebResponse[]::new);
    }

}
