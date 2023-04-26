package com.team23.tickets.Entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "solicitud")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
  private String descripcionSolucion;

  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_creacion")
  @Basic(optional = false)
  private Date fechaCreacion;

  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_cierre")
  @Basic(optional = true)
  private Date fechaCierre;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario_crea", referencedColumnName = "id_usuario")
  private Usuario usuarioCrea;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario_cierre", referencedColumnName = "id_usuario")
  private Usuario usuarioCierre;

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario_asignado", referencedColumnName = "id_usuario")
  private Usuario usuarioAsignado;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_solicitud")
  @JsonManagedReference
  private List<Seguimiento> seguimiento= new ArrayList<>();



}
