package com.team23.tickets.Entities;

import com.fasterxml.jackson.annotation.*;
import com.team23.tickets.DTO.SolicitudDTO;
import com.team23.tickets.Enum.Roles;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "usuario")
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Integer idUsuario;

  @Column(name = "nombres")
  private String nombres;

  @Column(name = "apellidos")
  private String apellidos;

  @Column(name = "telefono")
  private String telefono;

  @Column(name = "email")
  private String email;

  @Column(name = "activo", columnDefinition = "boolean default true")
  private boolean activo;

  @Column(name = "fecha_creacion")
  @Temporal(TemporalType.DATE)
  private Date fechaCreacion;

  @Column(name = "password")
  private String password;

  @Column(name = "id_usuario_crea")
  @Basic(optional =  true)
  private Integer idUsuarioCrea;

  @Column(name = "rol")
  @Enumerated(EnumType.STRING)
  private Roles rol;

  @Column(name = "nombre_avatar")
  private String nombre_avatar;

  /*@OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario_crea")
  @JsonManagedReference
  private List<Solicitud> solicitudes_credas = new ArrayList<>();
*/


  /*public void agregarSolicitud(Solicitud solicitud){
    solicitud.setUsuarioCrea(this);
    this.solicitudes_credas.add(solicitud);

  }*/

}
