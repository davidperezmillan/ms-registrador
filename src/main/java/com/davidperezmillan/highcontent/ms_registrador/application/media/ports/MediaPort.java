package com.davidperezmillan.highcontent.ms_registrador.application.media.ports;

import com.davidperezmillan.highcontent.ms_registrador.domain.media.models.Media;

public interface MediaPort {


    Media[] search(String title);

    Media getFicha(long id);
}
