package pl.edu.tirex.lvlup.api.models.service;

public class ServiceInfo
{
    private final int id;
    private boolean active;
    private final int planId;
    private String name;
    private long payedTo;
    private final long createdAt;

    public ServiceInfo(int id, boolean active, int planId, String name, long payedTo, long createdAt)
    {
        this.id = id;
        this.active = active;
        this.planId = planId;
        this.name = name;
        this.payedTo = payedTo;
        this.createdAt = createdAt;
    }

    public int getId()
    {
        return id;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public int getPlanId()
    {
        return planId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getPayedTo()
    {
        return payedTo;
    }

    public void setPayedTo(long payedTo)
    {
        this.payedTo = payedTo;
    }

    public long getCreatedAt()
    {
        return createdAt;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("ServiceInfo{");
        sb.append("id=").append(id);
        sb.append(", active=").append(active);
        sb.append(", planId=").append(planId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", payedTo=").append(payedTo);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
