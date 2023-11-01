package dao.modelo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    private String id;

    @NotEmpty
    private String name;

    private String password;

    private LocalDateTime fecha;

    public Usuario(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
