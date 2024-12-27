package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.SceneWebResponse;
import org.modelmapper.ModelMapper;

import java.util.Arrays;


public class SceneWebResponseMapper {

    public static SceneWebResponse map(Scene source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Scene.class, SceneWebResponse.class)
                .addMapping(src -> src.getPosters().getLarge(), SceneWebResponse::setPosters)
                .addMapping(src -> mapTags(source), SceneWebResponse::setTags);

        return modelMapper.map(source, SceneWebResponse.class);
    }

    public static SceneWebResponse[] map(Scene[] source) {
        return Arrays.stream(source)
                .map(SceneWebResponseMapper::map)
                .toArray(SceneWebResponse[]::new);
    }


    private static String[] mapTags(Scene src) {
        if (src.getTags() == null) {

            return new String[0];
        }
        String[] tags = new String[src.getTags().length];
        for (int i = 0; i < src.getTags().length; i++) {
            tags[i] = src.getTags()[i].getName();
        }

        return tags;
    }
}
