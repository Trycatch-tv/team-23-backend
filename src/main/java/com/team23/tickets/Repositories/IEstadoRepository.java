package com.team23.tickets.Repositories;

import com.team23.tickets.Entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado,Integer> {

    @Query("Select estados from Estado estados where estados.activo = true")
    List<Estado> listarEstados();

    List<Estado> findEstadosByIdEstado(@Param("id") Integer id);

    Optional<Estado> findEstadoByNombreAndActivo(@Param("nombre") String nombre, @Param("activo") boolean activo);

}
