package pl.edu.tirex.lvlup.api.models;

public class Page
{
    private int page;

    public Page(int page)
    {
        this.page = page;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("page=").append(page);
        sb.append('}');
        return sb.toString();
    }
}
