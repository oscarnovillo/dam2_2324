package com.example.demo.data.modelo;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {


    @Min(0)
    private int id;
    @Size(min = 2, max = 30)
    private String name;

}
