package com.team23.tickets.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.team23.tickets.Enum.Roles;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

  @Column(name = "fechaCreacion")
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

}
