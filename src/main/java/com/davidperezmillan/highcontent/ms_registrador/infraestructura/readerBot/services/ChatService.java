package com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ChatsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.mappers.ChatMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements ChatsPort {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public List<Chat> getAllChats() {
        return ChatMapper.map(chatRepository.findAll());
    }

    @Override
    public Chat getChat(Chat chat) {
        return ChatMapper.map(
                chatRepository.findById(
                        chat.getId()).orElseThrow(() -> new RuntimeException("Chat not found")
                )
        );
    }
}
