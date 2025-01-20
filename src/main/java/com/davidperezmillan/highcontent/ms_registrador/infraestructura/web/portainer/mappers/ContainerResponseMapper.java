package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.portainer.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.portainer.models.ContainerResponse;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

public class ContainerResponseMapper {

    public static ContainerResponse map(Container source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Container.class, ContainerResponse.class);

        return modelMapper.map(source, ContainerResponse.class);
    }

    public static ContainerResponse[] map(Container[] source) {
        return Arrays.stream(source)
                .map(ContainerResponseMapper::map)
                .toArray(ContainerResponse[]::new);
    }
}
