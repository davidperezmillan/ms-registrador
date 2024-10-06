package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.controllers.readerBot;


import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.RecoverParamsUseCase;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.UpdateParamsUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.readerBot.ParamRequest;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.dtos.readerBot.ParamResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.mappers.readerBot.ParamWebMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader_bot") // Añade el prefijo /files a todos los endpoinds de este controlador
@Log4j2
public class ParamsController {

    private final RecoverParamsUseCase recoverParamsUseCase;
    private final UpdateParamsUseCase updateParamsUseCase;

    public ParamsController(RecoverParamsUseCase recoverParamsUseCase, UpdateParamsUseCase updateParamsUseCase) {
        this.recoverParamsUseCase = recoverParamsUseCase;
        this.updateParamsUseCase = updateParamsUseCase;
    }

    @GetMapping("/param")
    public ResponseEntity<List<ParamResponse>> getParams() {
        try {
            return ResponseEntity.ok(ParamWebMapper.map(recoverParamsUseCase.recoverParams()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/param/{key}")
    public ResponseEntity<ParamResponse> getParamByKey(@PathVariable String key) {
        try {
            return ResponseEntity.ok(ParamWebMapper.mapToResponse(recoverParamsUseCase.recoverParamById(key)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/param")
    public ResponseEntity<ParamResponse> updateParam(@RequestBody ParamRequest paramRequest) {
        try {
            log.info("updateParam: {}", paramRequest);
            return ResponseEntity.ok(ParamWebMapper.mapToResponse(updateParamsUseCase.updateParam(ParamWebMapper.map(paramRequest))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
