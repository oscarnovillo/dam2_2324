package dao.modelo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioGetDTO {

    @NotEmpty
    private String name;
}
