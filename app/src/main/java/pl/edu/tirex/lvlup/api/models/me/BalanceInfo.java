package pl.edu.tirex.lvlup.api.models.me;

public class BalanceInfo
{
    private String currency;
    private double balance;
    private String balancePretty;

    public BalanceInfo(String currency, double balance, String balancePretty)
    {
        this.currency = currency;
        this.balance = balance;
        this.balancePretty = balancePretty;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public String getBalancePretty()
    {
        return balancePretty;
    }

    public void setBalancePretty(String balancePretty)
    {
        this.balancePretty = balancePretty;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("BalanceInfo{");
        sb.append("currency='").append(currency).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", balancePretty='").append(balancePretty).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
