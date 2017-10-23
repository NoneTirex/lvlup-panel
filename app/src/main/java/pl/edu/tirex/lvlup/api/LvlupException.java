package pl.edu.tirex.lvlup.api;

import pl.edu.tirex.lvlup.api.models.LvlupError;

import java.io.IOException;

public class LvlupException extends IOException
{
    private final int statusCode;
    private final LvlupError error;

    public LvlupException(int statusCode, LvlupError error)
    {
        this.statusCode = statusCode;
        this.error = error;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public LvlupError getError()
    {
        return error;
    }

    @Override
    public String getMessage()
    {
        return this.error.getMessage() + " [" + this.error.getErrorCode() + "]";
    }
}
