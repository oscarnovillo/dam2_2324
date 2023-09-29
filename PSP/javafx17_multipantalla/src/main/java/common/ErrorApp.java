package common;



import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public  abstract sealed class ErrorApp permits Error2,Error3,Error4{

    private final String mensaje;
    private final LocalDateTime fecha;

    protected ErrorApp(String mensaje) {
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }

    protected ErrorApp(String mensaje, LocalDateTime fecha) {
        this.mensaje = mensaje;
        this.fecha = fecha;
    }



}
