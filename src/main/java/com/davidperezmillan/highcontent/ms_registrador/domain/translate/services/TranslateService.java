package com.davidperezmillan.highcontent.ms_registrador.domain.translate.services;

import com.davidperezmillan.highcontent.ms_registrador.domain.translate.usecases.TranslateUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.translate.services.TranslateAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateService implements TranslateUseCase {

    private final TranslateAIService translateAIService;

    @Autowired
    public TranslateService(TranslateAIService translateAIService) {
        this.translateAIService = translateAIService;
    }

    @Override
    public String translate(String text) {
        return translateAIService.translate(text);
    }
}