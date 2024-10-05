package com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.repositories;


import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.models.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository  extends JpaRepository<ChatModel, Long> {

}
