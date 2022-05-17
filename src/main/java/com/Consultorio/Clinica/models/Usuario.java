package com.Consultorio.Clinica.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
//Anotacion para otorgar el papel como referencia de la tabla en la DB con el nombre "usuarios"
@Table(name = "usuarios")
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

}
