package com.davidperezmillan.highcontent.ms_registrador.application.services.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ParamsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.UpdateParamsUseCase;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UpdateParamsService implements UpdateParamsUseCase {

    private final ParamsPort paramsPort;

    public UpdateParamsService(ParamsPort paramsPort) {
        this.paramsPort = paramsPort;
    }

    @Override
    public Param updateParam(Param param) {
        param = deleteDuplicateValues(param);
        return paramsPort.updateParam(param);
    }

    private Param deleteDuplicateValues(Param param) {
        String[] values = param.getValue().split(",");
        String[] uniqueValues = skipDuplicateString(values);
        param.setValue(convertArrayToString(uniqueValues));
        return param;
    }

    private String convertArrayToString(String[] array) {
        return String.join(",", array);
    }

    private String[] skipDuplicateString(String[] array) {
        Set<String> uniqueStrings = new HashSet<>(Arrays.asList(array));
        return uniqueStrings.toArray(new String[0]);
    }
}
