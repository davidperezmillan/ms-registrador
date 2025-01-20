package com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.mappers;

import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.models.ContainerPortainerResponse;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

public class ContainerPortainerResponseMapper {

    public static Container map(ContainerPortainerResponse source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(ContainerPortainerResponse.class, Container.class);

        return modelMapper.map(source, Container.class);
    }

    public static Container[] map(ContainerPortainerResponse[] source) {
        return Arrays.stream(source)
                .map(ContainerPortainerResponseMapper::map)
                .toArray(Container[]::new);
    }
}
