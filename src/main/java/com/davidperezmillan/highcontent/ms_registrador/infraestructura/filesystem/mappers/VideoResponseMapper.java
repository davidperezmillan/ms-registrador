package com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.dtos.VideoResponse;
import org.modelmapper.ModelMapper;

public interface VideoResponseMapper {


    /* mapper to convert VideoResponse to VideoFile */
    static VideoFile map(VideoResponse videoResponse) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(videoResponse, VideoFile.class);
    }

}
