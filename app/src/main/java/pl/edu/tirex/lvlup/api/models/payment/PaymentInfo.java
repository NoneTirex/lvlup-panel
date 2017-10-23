package pl.edu.tirex.lvlup.api.models.payment;

public class PaymentInfo
{
    private final int id;
    private double amount;
    private String description;
    private long createdAt;

    public PaymentInfo(int id, double amount, String description, long createdAt)
    {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
    }

    public int getId()
    {
        return id;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public long getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(long createdAt)
    {
        this.createdAt = createdAt;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PaymentInfo{");
        sb.append("id=").append(id);
        sb.append(", amount=").append(amount);
        sb.append(", description='").append(description).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
