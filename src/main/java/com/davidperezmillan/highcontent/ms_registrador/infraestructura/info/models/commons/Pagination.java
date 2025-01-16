package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons;

import lombok.Data;

@Data
public class Pagination {
    private int results;
    private int limit;
    private int prev;
    private int next;
}
