package com.davidperezmillan.highcontent.ms_registrador.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Chat {

    private Long id;
    private String name;
    private String type;
    private LocalDateTime dateModified;

    private Boolean videoActive;
}
