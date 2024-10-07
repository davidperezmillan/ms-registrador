package com.davidperezmillan.highcontent.ms_registrador.application.services.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ChatsPort;
import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ParamsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.RecoverChatsUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoverChatsService implements RecoverChatsUseCase {

    private final ChatsPort chatsPort;
    private final ParamsPort paramsPort;

    @Autowired
    public RecoverChatsService(ChatsPort chatsPort, ParamsPort paramsPort) {
        this.chatsPort = chatsPort;
        this.paramsPort = paramsPort;
    }

    @Override
    public List<Chat> recoverChats() {
        Param param = new Param();
        param.setKy("TELEGRAM_CHAT_ID_READER");
        paramsPort.getParam(param);

        String paramVideos = paramsPort.getParam(param).getValue();
        String[] chatsArray = paramVideos.split(",");

        List<Chat> chats = chatsPort.getAllChats();

        for (Chat c : chats) {
            // comprobar si el chat esta en la lista de chats
            for (String chatId : chatsArray) {
                if (c.getId() == Long.parseLong(chatId.trim())) {
                    c.setVideoActive(true);
                }
            }
        }
        return chats;
    }

    @Override
    public Chat recoverChatById(long id) {
        Chat chat = new Chat();
        chat.setId(id);
        return chatsPort.getChat(chat);
    }
}
