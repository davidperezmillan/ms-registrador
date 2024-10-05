package com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "params")
@Data
public class ParamsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;


}
