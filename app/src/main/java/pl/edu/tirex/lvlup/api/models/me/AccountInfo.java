package pl.edu.tirex.lvlup.api.models.me;

public class AccountInfo
{
    private final int id;
    private String fullname;
    private String username;
    private String email;

    public AccountInfo(int id, String fullname, String username, String email)
    {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
    }

    public int getId()
    {
        return id;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("AccountInfo{");
        sb.append("id=").append(id);
        sb.append(", fullname='").append(fullname).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
