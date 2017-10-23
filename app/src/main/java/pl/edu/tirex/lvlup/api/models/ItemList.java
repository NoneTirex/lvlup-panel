package pl.edu.tirex.lvlup.api.models;

import java.util.ArrayList;
import java.util.List;

public class ItemList<T>
{
    private final Paging paging;
    private final List<T> items;

    public ItemList(Paging paging)
    {
        this(paging, new ArrayList<T>());
    }

    public ItemList(Paging paging, List<T> items)
    {
        this.paging = paging;
        this.items = items;
    }

    public Paging getPaging()
    {
        return paging;
    }

    public List<T> getItems()
    {
        return items;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("ItemList{");
        sb.append("paging=").append(paging);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
