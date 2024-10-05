package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers.readerBot;


import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.RecoverParamsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reader_bot") // Añade el prefijo /files a todos los endpoinds de este controlador
public class ParamsController {

    private final RecoverParamsUseCase recoverParamsUseCase;

    public ParamsController(RecoverParamsUseCase recoverParamsUseCase) {
        this.recoverParamsUseCase = recoverParamsUseCase;
    }

    @GetMapping("/param")
    public List<Param> getParams() {
        try {
            return recoverParamsUseCase.recoverParams();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/param/{key}")
    public Param getParamByKey(@PathVariable String key) {
        try {
            return recoverParamsUseCase.recoverParamById(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
