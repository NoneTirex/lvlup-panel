package pl.edu.tirex.lvlup.api.models;

import pl.edu.tirex.lvlup.api.LvlupException;

public class LvlupStatus<T>
{
    private final T status;
    private final LvlupException exception;

    public LvlupStatus(T status, LvlupException exception)
    {
        this.status = status;
        this.exception = exception;
    }

    public T getStatus()
    {
        return status;
    }

    public LvlupException getException()
    {
        return exception;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("LvlupStatus{");
        sb.append("status=").append(status);
        sb.append(", exception=").append(exception);
        sb.append('}');
        return sb.toString();
    }
}
