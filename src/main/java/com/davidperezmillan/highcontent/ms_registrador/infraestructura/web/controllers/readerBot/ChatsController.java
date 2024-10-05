package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers.readerBot;


import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.RecoverChatsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reader_bot") // Añade el prefijo /files a todos los endpoinds de este controlador
public class ChatsController {

    private final RecoverChatsUseCase recoverChatsUseCase;

    public ChatsController(RecoverChatsUseCase recoverChatsUseCase) {
        this.recoverChatsUseCase = recoverChatsUseCase;
    }


    @GetMapping("/chats")
    public List<Chat> getChats() {
        try {
            return recoverChatsUseCase.recoverChats();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
