package com.davidperezmillan.highcontent.ms_registrador.application.services.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ChatsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.RecoverChatsUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoverChatsService implements RecoverChatsUseCase {

    private final ChatsPort chatsPort;

    @Autowired
    public RecoverChatsService(ChatsPort chatsPort) {
        this.chatsPort = chatsPort;
    }

    @Override
    public List<Chat> recoverChats() {
        List<Chat> chats = chatsPort.getAllChats();
        return chats;
    }
}
