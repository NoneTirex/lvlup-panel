package pl.edu.tirex.lvlup.api.models;

public class LvlupError
{
    private final boolean error;
    private final int errorCode;
    private final String status;
    private final String message;

    public LvlupError(boolean error, int errorCode, String status, String message)
    {
        this.error = error;
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
    }

    public boolean isError()
    {
        return error;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public String getStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("LvlupError{");
        sb.append("error=").append(error);
        sb.append(", errorCode=").append(errorCode);
        sb.append(", status='").append(status).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
