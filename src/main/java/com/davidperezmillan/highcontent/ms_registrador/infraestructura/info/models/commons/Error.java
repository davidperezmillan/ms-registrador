package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons;

import lombok.Data;

@Data
public class Error {
    private int code;
    private int httpCode;
    private String message;
}
