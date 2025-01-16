package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.mappers.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.media.models.Media;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.SearchResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.Fichas;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.Info;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

public class SearchResponseMapper {

    public static Media map(SearchResponse source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(SearchResponse.class, Media.class);
        return modelMapper.map(source, Media.class);
    }

    public static Media[] map(SearchResponse[] source) {
        return Arrays.stream(source)
                .map(SearchResponseMapper::map)
                .toArray(Media[]::new);
    }

    public static Media map(Fichas source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Fichas.class, Media.class);
        return modelMapper.map(source, Media.class);
    }

    public static Media[] map(List<Fichas> source) {
        return source.stream()
                .map(SearchResponseMapper::map)
                .toArray(Media[]::new);
    }

    public static Media map(Info source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Fichas.class, Media.class);
        return modelMapper.map(source, Media.class);
    }
}
