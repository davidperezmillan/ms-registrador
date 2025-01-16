package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.Error;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.Requests;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.ResultFicha;
import lombok.Data;

@Data
public class FichaResponse {
    private Error error;
    private Requests requests;
    private ResultFicha result;
}
