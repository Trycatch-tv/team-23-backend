package com.team23.tickets.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "seguimiento")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seguimiento implements Serializable {

  private static final long serialVersionUID = 1L;


  @Id
  @Column(name = "id_solicitud")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idSeguimiento;

  @Column(name = "descripcion")
  @Basic(optional = false)
  private String descripcion;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "id_solicitud")
  @Transient
  private Solicitud solicitud;

  @Column(name = "activo", columnDefinition = "boolean default true")
  private boolean activo;

  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_creacion")
  private Date fechaCreacion;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;


}
