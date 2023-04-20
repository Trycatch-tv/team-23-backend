package com.team23.tickets.Repositories;

import com.team23.tickets.Entities.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeguimientoRepository extends JpaRepository<Seguimiento, Long> {
}
