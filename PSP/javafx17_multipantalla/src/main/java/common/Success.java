package common;

public final class Success<T> extends ResultMio<T> {
    public Success(T data) {
        super(data, null);
    }
}
