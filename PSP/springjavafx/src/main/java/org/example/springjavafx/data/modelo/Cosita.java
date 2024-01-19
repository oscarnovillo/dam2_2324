package org.example.springjavafx.data.modelo;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cositas")
public class Cosita {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "cosa_id")
    private Cosa cosa;

}
