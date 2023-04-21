package com.team23.tickets.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "solicitud")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Solicitud implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_solicitud")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  private long idSolicitud;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_tipo_solicitud")
  private TipoSolicitud tipoSolicitud;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_estado")
  private Estado estado;

  @Column(name = "descripcion_solicitud")
  @Basic(optional = false)
  private String descripcionSolicitud;

  @Column(name = "descripcion_solucion")
  @Basic(optional = false)
  private String descripcionSolucion;

  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_creacion")
  @Basic(optional = false)
  private Date fechaCreacion;

  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_cierre")
  @Basic(optional = true)
  private Date fechaCierre;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario_crea")
  @JsonIgnore
  @Transient
  private Usuario usuarioCrea;

  @OneToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario_cierre")
  @JsonIgnore
  @Transient
  private Usuario usuarioCierre;


  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "id_solicitud")
  private Set<Seguimiento> seguimiento= new HashSet<>();



}
