package pl.edu.tirex.lvlup.api.models.vps;

public class DDoSInfo
{
    private final int id;
    private final long startedAt;
    private final long endedAt;

    public DDoSInfo(int id, long startedAt, long endedAt)
    {
        this.id = id;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public int getId()
    {
        return id;
    }

    public long getStartedAt()
    {
        return startedAt;
    }

    public long getEndedAt()
    {
        return endedAt;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("IpDDoS{");
        sb.append("id=").append(id);
        sb.append(", startedAt=").append(startedAt);
        sb.append(", endedAt=").append(endedAt);
        sb.append('}');
        return sb.toString();
    }
}
