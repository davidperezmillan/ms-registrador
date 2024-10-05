package com.davidperezmillan.highcontent.ms_registrador.infraestructura.bbdd.readerBot.repositories;


import com.davidperezmillan.highcontent.ms_registrador.infraestructura.bbdd.readerBot.models.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository  extends JpaRepository<ChatModel, Long> {

}
