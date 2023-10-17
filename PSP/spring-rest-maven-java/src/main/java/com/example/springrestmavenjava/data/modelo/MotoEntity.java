package com.example.springrestmavenjava.data.modelo;

import com.example.springrestmavenjava.domain.modelo.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "motos")
public class MotoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
