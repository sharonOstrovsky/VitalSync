package com.example.vitalsync.entity;

import com.example.vitalsync.utils.Rol;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clave;
    private String email;
    @Enumerated (EnumType.STRING)
    private Rol rol;
}
