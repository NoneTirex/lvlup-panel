package pl.edu.tirex.lvlup.api.models;

public class Paging
{
    private int currentPage;
    private int totalPages;
    private int itemsPerPage;

    public Paging(int currentPage, int totalPages, int itemsPerPage)
    {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.itemsPerPage = itemsPerPage;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public int getItemsPerPage()
    {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage)
    {
        this.itemsPerPage = itemsPerPage;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Paging{");
        sb.append("currentPage=").append(currentPage);
        sb.append(", totalPages=").append(totalPages);
        sb.append(", itemsPerPage=").append(itemsPerPage);
        sb.append('}');
        return sb.toString();
    }
}
