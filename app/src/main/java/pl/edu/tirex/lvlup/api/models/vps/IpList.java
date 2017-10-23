package pl.edu.tirex.lvlup.api.models.vps;

import java.util.ArrayList;
import java.util.List;

public class IpList
{
    private final String main;
    private final List<String> additional;

    public IpList(String main)
    {
        this(main, new ArrayList<>());
    }

    public IpList(String main, List<String> additional)
    {
        this.main = main;
        this.additional = additional;
    }

    public String getMain()
    {
        return main;
    }

    public List<String> getAdditional()
    {
        return additional;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("IpList{");
        sb.append("main='").append(main).append('\'');
        sb.append(", additional=").append(additional);
        sb.append('}');
        return sb.toString();
    }
}
