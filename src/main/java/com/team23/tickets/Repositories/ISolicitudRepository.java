package com.team23.tickets.Repositories;

import com.team23.tickets.Entities.Estado;
import com.team23.tickets.Entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query("Select solicitud from Solicitud solicitud where solicitud.estado <> '6'")
    List<Solicitud> listarSolicitudes();

}
