package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons;

import lombok.Data;

import java.util.List;

@Data
public class Ficha {
    private Pagination pagination;
    private List<Fichas> fichas;
}
