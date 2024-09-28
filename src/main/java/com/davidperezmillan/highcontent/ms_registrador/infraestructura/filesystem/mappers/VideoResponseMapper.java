package com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.filesystem.dtos.VideoResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface VideoResponseMapper {

    static VideoFile map(VideoResponse videoResponse) {
        ModelMapper modelMapper = new ModelMapper();

        // Custom converter for String to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
        Converter<String, LocalDateTime> toLocalDateTimeConverter = new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> context) {
                return context.getSource() == null ? null : LocalDateTime.parse(context.getSource(), formatter);
            }
        };

        // Mapper configuration
        modelMapper.createTypeMap(VideoResponse.class, VideoFile.class)
                .addMappings(mapper -> mapper.using(toLocalDateTimeConverter)
                        .map(VideoResponse::getCreationDate, VideoFile::setCreationDate));

        return modelMapper.map(videoResponse, VideoFile.class);
    }
}
