package com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;

import java.util.List;

public interface RecoverChatsUseCase {

    List<Chat> recoverChats();

    Chat recoverChatById(long id);
}
