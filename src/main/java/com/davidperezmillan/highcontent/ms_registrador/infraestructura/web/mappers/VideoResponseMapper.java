package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.VideoResponse;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public interface VideoResponseMapper {

    /* metodo utilizando modelmapper para convertir una lista de videofile a una lista de videoresponse */
    static List<VideoResponse> map(List<VideoFile> videofile) {
        return videofile.stream()
                .map(videoFile -> map(videoFile))
                .collect(Collectors.toList());
    }


    static VideoResponse map(VideoFile videoFile) {
        ModelMapper modelMapper = new ModelMapper();

        // Custom converter for LocalDateTime to String
        Converter<LocalDateTime, String> toStringDateConverter = new Converter<LocalDateTime, String>() {
            @Override
            public String convert(MappingContext<LocalDateTime, String> context) {
                return context.getSource() == null ? null : context.getSource().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        };

        // map with modelmapper size to human format
        modelMapper.createTypeMap(VideoFile.class, VideoResponse.class)
                .addMappings(mapper -> mapper.using(context -> formatSize((Long) context.getSource()))
                        .map(VideoFile::getSize, VideoResponse::setSize))
                // map with modelmapper creationDate to string
                .addMappings(mapper -> mapper.using(toStringDateConverter)
                        .map(VideoFile::getCreationDate, VideoResponse::setCreationDate))
                // add field delete_link
                .addMappings(mapper -> mapper.using(context -> buildDeleteLink((String) context.getSource()))
                        .map(VideoFile::getFileName, VideoResponse::setDeleteLink))
                .addMappings(mapper -> mapper.using(context -> buildGenerateVideoLink((String) context.getSource()))
                        .map(VideoFile::getFileName, VideoResponse::setGenerateVideoLink));


        return modelMapper.map(videoFile, VideoResponse.class);
    }

    static String buildGenerateVideoLink(String fileName) {
        return "files/videos/generate/" + fileName;
    }
    static String buildDeleteLink(String fileName) {
        return "files/videos/" + fileName;
    }

    // convert size to format human string
    static String formatSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        // forzar el separador numerico a punto
        return String.format("%.1f %s", size / Math.pow(1024, digitGroups), units[digitGroups]).replace(",", ".");
    }

}
