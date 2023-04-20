package com.team23.tickets.Repositories;

import com.team23.tickets.Entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolicitudRepository extends JpaRepository<Solicitud, Long> {

}
