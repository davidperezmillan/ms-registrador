package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.readerBot;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParamRequest {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("value")
    private String value;
}
