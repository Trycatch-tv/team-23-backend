package com.team23.tickets.Repositories;

import com.team23.tickets.Entities.Estado;
import com.team23.tickets.Entities.TipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoSolicitudRepository extends JpaRepository<TipoSolicitud, Integer> {

    @Query("Select tipos from TipoSolicitud tipos where tipos.activo = true")
    List<TipoSolicitud> listarTiposSolicitud();

}
