package com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.models.ChatModel;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public interface ChatMapper {

    static List<Chat> map(List<ChatModel> chats) {
        return chats.stream()
                .map(ChatMapper::map)
                .collect(Collectors.toList());
    }

    static Chat map(ChatModel chatResponse) {
        ModelMapper modelMapper = new ModelMapper();


        return modelMapper.map(chatResponse, Chat.class);
    }


}
