package com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.repositories;


import com.davidperezmillan.highcontent.ms_registrador.infraestructura.readerBot.models.ParamsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParamsRepository extends JpaRepository<ParamsModel, Long> {

    Optional<ParamsModel> findByky(String ky);
}
