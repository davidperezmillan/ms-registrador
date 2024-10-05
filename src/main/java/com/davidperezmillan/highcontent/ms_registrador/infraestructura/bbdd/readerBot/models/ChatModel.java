package com.davidperezmillan.highcontent.ms_registrador.infraestructura.bbdd.readerBot.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "chats")
@Data
public class ChatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "date_modified", nullable = false)
    private LocalDateTime dateModified;

}