package common;

import lombok.Data;

@Data
public abstract sealed class ResultMio<T> permits ErrorTest, Loading, Success {



    private final T data;
    private final String mensaje;


}
