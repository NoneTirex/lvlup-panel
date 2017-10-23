package pl.edu.tirex.lvlup.api.models.vps;

import com.google.gson.annotations.SerializedName;

public class VpsInfo
{
    private final int id;
    private final String ip;
    private final int machineId;
    private final long createdAt;
    private String name;
    private boolean active;
    private long payedTo;
    private int planId;

    @SerializedName("virt")
    private int virtualization;

    public VpsInfo(int id, String name, String ip, int machineId, long createdAt)
    {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.machineId = machineId;
        this.createdAt = createdAt;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIp()
    {
        return ip;
    }

    public int getMachineId()
    {
        return machineId;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
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

    public int getPlanId()
    {
        return planId;
    }

    public void setPlanId(int planId)
    {
        this.planId = planId;
    }

    public int getVirtualization()
    {
        return virtualization;
    }

    public void setVirtualization(int virtualization)
    {
        this.virtualization = virtualization;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("VpsInfo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", machineId=").append(machineId);
        sb.append(", active=").append(active);
        sb.append(", payedTo=").append(payedTo);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", planId=").append(planId);
        sb.append(", virt=").append(virtualization);
        sb.append('}');
        return sb.toString();
    }
}
