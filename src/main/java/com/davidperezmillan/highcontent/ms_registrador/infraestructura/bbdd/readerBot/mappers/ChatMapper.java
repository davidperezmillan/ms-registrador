package com.davidperezmillan.highcontent.ms_registrador.infraestructura.bbdd.readerBot.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.bbdd.readerBot.models.ChatModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public interface ChatMapper {

    static List<Chat> map(List<ChatModel> chats) {
        return chats.stream()
                .map(chat -> map(chat))
                .collect(Collectors.toList());
    }

    static Chat map(ChatModel chatResponse) {
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
        modelMapper.createTypeMap(ChatModel.class, Chat.class)
                .addMappings(mapper -> mapper.using(toLocalDateTimeConverter)
                        .map(ChatModel::getDateModified, Chat::setDateModified));

        return modelMapper.map(chatResponse, Chat.class);
    }


}
