package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.media.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.media.models.Media;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.media.models.MediaResponse;
import org.modelmapper.ModelMapper;

import java.util.Arrays;


public class MediaResponseMapper {

    public static MediaResponse map(Media source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Media.class, MediaResponse.class);

        return modelMapper.map(source, MediaResponse.class);
    }

    public static MediaResponse[] map(Media[] source) {
        return Arrays.stream(source)
                .map(MediaResponseMapper::map)
                .toArray(MediaResponse[]::new);
    }

}
