package dao.modelo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioLoginDTO {

    private String id;

    @NotEmpty
    private String name;

    private String password;
}
