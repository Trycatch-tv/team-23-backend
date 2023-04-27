package com.team23.tickets.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovimientosEstadoDTO {


  private Integer idMovimiento;

  private Integer idEstadoFinal;

}
