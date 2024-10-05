package com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;

import java.util.List;

public interface ChatsPort {

    List<Chat> getAllChats();
}
