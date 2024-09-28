package com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.dtos.VideoResponse;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public interface VideoResponseMapper {


    /* mapper to convert VideoResponse to VideoFile */
    static VideoFile map(VideoResponse videoResponse) {
        ModelMapper modelMapper = new ModelMapper();

        // mapper videoResponse to VideoFile
        // convert CreationDate to LocalDateTime
        modelMapper.createTypeMap(VideoResponse.class, VideoFile.class)
                .addMappings(mapper -> mapper.using(context -> LocalDateTime.parse((String) context.getSource()))
                        .map(VideoResponse::getCreationDate, VideoFile::setCreationDate));

        return modelMapper.map(videoResponse, VideoFile.class);
    }

}
