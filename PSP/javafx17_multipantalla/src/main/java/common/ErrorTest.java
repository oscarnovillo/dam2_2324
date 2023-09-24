package common;

public final class ErrorTest<T> extends ResultMio<T> {
    public ErrorTest(String mensaje) {
        super(null, mensaje);
    }
}
