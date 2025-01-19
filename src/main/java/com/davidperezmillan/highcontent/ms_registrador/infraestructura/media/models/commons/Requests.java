package com.davidperezmillan.highcontent.ms_registrador.infraestructura.media.models.commons;

import lombok.Data;

@Data
public class Requests {
    private int rateLimit;
    private int blockExpiresIn;
}
