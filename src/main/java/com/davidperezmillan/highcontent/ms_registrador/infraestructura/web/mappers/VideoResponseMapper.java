package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.VideoResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public interface VideoResponseMapper {

    /* metodo utilizando modelmapper para convertir una lista de videofile a una lista de videoresponse */
    static List<VideoResponse> map(List<VideoFile> searchPlayMaxResponse) {
        ModelMapper modelMapper = new ModelMapper();

        return searchPlayMaxResponse.stream()
                .map(searchPlayMaxResponse1 -> modelMapper.map(searchPlayMaxResponse1, VideoResponse.class))
                .collect(Collectors.toList());
    }


    static VideoResponse map(VideoFile searchPlayMaxResponse) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(searchPlayMaxResponse, VideoResponse.class);
    }

}
