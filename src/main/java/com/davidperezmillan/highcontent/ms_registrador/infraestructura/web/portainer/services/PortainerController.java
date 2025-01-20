package com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.portainer.services;

import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.models.Container;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.usecases.GetContainerUseCase;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.usecases.ProccessContainerUseCase;
import com.davidperezmillan.highcontent.ms_registrador.domain.portainer.usecases.StartContainerUseCase;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.portainer.mappers.ContainerResponseMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.portainer.models.ContainersResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.web.portainer.models.StartContainer;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/portainer")
public class PortainerController {

    private final GetContainerUseCase getContainerUseCase;
    private final StartContainerUseCase startContainerUseCase;
    private final ProccessContainerUseCase proccessContainerUseCase;

    public PortainerController(GetContainerUseCase getContainerUseCase,
                               StartContainerUseCase startContainerUseCase,
                               ProccessContainerUseCase proccessContainerUseCase) {
        this.getContainerUseCase = getContainerUseCase;
        this.startContainerUseCase = startContainerUseCase;
        this.proccessContainerUseCase = proccessContainerUseCase;
    }

    /**
     * get Container
     */
    @GetMapping("/container")
    public ResponseEntity<ContainersResponse> getContainer() {
        Container[] Container = getContainerUseCase.getContainer();
        ContainersResponse containersResponse = new ContainersResponse(ContainerResponseMapper.map(Container));
        return new ResponseEntity<>(containersResponse, HttpStatus.OK);
    }

    @GetMapping("/stop")
    public ResponseEntity<ContainersResponse> getContainerStop() {
        Container[] Container = getContainerUseCase.getContainerStop();
        ContainersResponse containersResponse = new ContainersResponse(ContainerResponseMapper.map(Container));
        return new ResponseEntity<>(containersResponse, HttpStatus.OK);
    }


    @PostMapping("/start")
    public ResponseEntity<Void> getContainerStart(@RequestBody StartContainer startContainer) {
        startContainerUseCase.startContainer(startContainer.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/process")
    public ResponseEntity<Void> getContainerProcess() {
        proccessContainerUseCase.processContainer();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
