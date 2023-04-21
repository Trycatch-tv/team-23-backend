package com.team23.tickets.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movimientos_estado")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovimientosEstado {

  @Id
  @Column(name = "id_movimiento")
  @Basic(optional = false)
  private Integer idMovimiento;

  @Column(name = "id_estado_actual")
  private Integer idEstadoActual;

  @Column(name = "id_estado_final")
  private Integer idEstadoFinal;

  @Column(name = "activo", columnDefinition = "boolean default true")
  private boolean activo;


}
