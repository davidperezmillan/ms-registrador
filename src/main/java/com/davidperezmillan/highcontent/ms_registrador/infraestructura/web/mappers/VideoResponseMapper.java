package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.VideoFile;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.VideoResponse;
import org.modelmapper.ModelMapper;

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

        // map with modelmapper size to human format
        modelMapper.createTypeMap(VideoFile.class, VideoResponse.class)
                .addMappings(mapper -> mapper.using(context -> formatSize((Long) context.getSource()))
                        .map(VideoFile::getSize, VideoResponse::setSize))
                // add field delete_link
                .addMappings(mapper -> mapper.using(context -> buildDeleteLink((String) context.getSource()))
                        .map(VideoFile::getFileName, VideoResponse::setDeleteLink));


        return modelMapper.map(videoFile, VideoResponse.class);
    }


    static String buildDeleteLink(String fileName) {
        return "files/delete/" + fileName;
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
