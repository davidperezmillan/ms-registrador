package com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TranslateResponse {

    @JsonProperty("translation_text")
    private String translationText;

}
