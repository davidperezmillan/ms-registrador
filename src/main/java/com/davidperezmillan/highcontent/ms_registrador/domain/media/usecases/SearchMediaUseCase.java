package com.davidperezmillan.highcontent.ms_registrador.domain.media.usecases;

import com.davidperezmillan.highcontent.ms_registrador.domain.media.models.Media;


public interface SearchMediaUseCase {

    Media[] search(String title);


}
