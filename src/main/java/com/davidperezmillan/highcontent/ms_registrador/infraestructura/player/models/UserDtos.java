package com.davidperezmillan.highcontent.ms_registrador.infraestructura.player.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDtos {


    @JsonProperty("Name")
    private String name;
    @JsonProperty("Id")
    private String id;
}
