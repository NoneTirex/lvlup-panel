package pl.edu.tirex.lvlup.api.models.auth;

public class LvlupLoginData
{
    private final String username;
    private final String password;

    public LvlupLoginData(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
