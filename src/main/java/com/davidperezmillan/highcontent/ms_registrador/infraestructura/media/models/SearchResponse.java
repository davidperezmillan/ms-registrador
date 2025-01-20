package com.davidperezmillan.highcontent.ms_registrador.infraestructura.media.models;


import com.davidperezmillan.highcontent.ms_registrador.infraestructura.media.models.commons.Error;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.media.models.commons.Requests;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.media.models.commons.ResultSearch;
import lombok.Data;

@Data
public class SearchResponse {

        private Error error;
        private Requests requests;
        private ResultSearch result;


}
