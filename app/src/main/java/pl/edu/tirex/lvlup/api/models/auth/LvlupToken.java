package pl.edu.tirex.lvlup.api.models.auth;

import com.google.gson.reflect.TypeToken;
import pl.edu.tirex.lvlup.api.IntExecutor;
import pl.edu.tirex.lvlup.api.LvlupApi;
import pl.edu.tirex.lvlup.api.RequestMethod;
import pl.edu.tirex.lvlup.api.models.ItemList;
import pl.edu.tirex.lvlup.api.models.Page;
import pl.edu.tirex.lvlup.api.models.me.AccountInfo;
import pl.edu.tirex.lvlup.api.models.me.BalanceInfo;
import pl.edu.tirex.lvlup.api.models.payment.PaymentInfo;
import pl.edu.tirex.lvlup.api.models.service.ServiceInfo;
import pl.edu.tirex.lvlup.api.models.vps.DDoSInfo;
import pl.edu.tirex.lvlup.api.models.vps.IpList;
import pl.edu.tirex.lvlup.api.models.vps.VpsInfo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LvlupToken
{
    private final String token;

    public LvlupToken(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }

    public AccountInfo getAccountInfo() throws IOException
    {
        return LvlupApi.query("/me", RequestMethod.GET, this.token, null, AccountInfo.class);
    }

    public BalanceInfo getBalanceInfo() throws IOException
    {
        return LvlupApi.query("/me/balance", RequestMethod.GET, this.token, null, BalanceInfo.class);
    }

    public ItemList<PaymentInfo> getPaymentList() throws IOException
    {
        return this.getPaymentList(0);
    }

    public ItemList<PaymentInfo> getPaymentList(int page) throws IOException
    {
        Type listType = new TypeToken<ItemList<PaymentInfo>>()
        {
        }.getType();
        return LvlupApi.query("/payment", RequestMethod.GET, this.token, page == 0 ? null : new Page(page), listType);
    }

    public ItemList<ServiceInfo> getServiceList() throws IOException
    {
        return this.getServiceList(0);
    }

    public ItemList<ServiceInfo> getServiceList(int page) throws IOException
    {
        Type listType = new TypeToken<ItemList<ServiceInfo>>()
        {
        }.getType();
        return LvlupApi.query("/service", RequestMethod.GET, this.token, page == 0 ? null : new Page(page), listType);
    }

    public ItemList<VpsInfo> getVpsList() throws IOException
    {
        return this.getVpsList(0);
    }

    public ItemList<VpsInfo> getVpsList(int page) throws IOException
    {
        Type listType = new TypeToken<ItemList<VpsInfo>>()
        {
        }.getType();
        return LvlupApi.query("/vps", RequestMethod.GET, this.token, page == 0 ? null : new Page(page), listType);
    }

    public IpList getVpsIpList(int id) throws IOException
    {
        return LvlupApi.query("/vps/" + id + "/ip", RequestMethod.GET, this.token, null, IpList.class);
    }

    public ItemList<DDoSInfo> getVpsIpDDoS(int id, String ip) throws IOException
    {
        Type listType = new TypeToken<ItemList<DDoSInfo>>()
        {
        }.getType();
        return LvlupApi.query("/vps/" + id + "/ip/" + ip + "/ddos", RequestMethod.GET, this.token, null, listType);
    }

    public <T> List<T> getAllPaging(IntExecutor<? extends ItemList<T>, IOException> executor) throws IOException
    {
        List<T> items = new ArrayList<>();
        int page = 0;
        ItemList<T> itemList = null;
        while ((itemList == null || itemList.getPaging().getTotalPages() > page) && (itemList = executor.execute(page)) != null)
        {
            items.addAll(itemList.getItems());
            page = itemList.getPaging().getCurrentPage() + 1;
        }
        return items;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("LvlupToken{");
        sb.append("token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
