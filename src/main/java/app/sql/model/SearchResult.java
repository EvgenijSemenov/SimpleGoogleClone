package app.sql.model;

import app.model.WebPage;

import java.util.List;

public class SearchResult {

    private String searchText;
    private List<WebPage> webPageList;
    private int count;

    public SearchResult(String searchText, List<WebPage> webPageList, int count) {
        this.searchText = searchText;
        this.webPageList = webPageList;
        this.count = count;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<WebPage> getWebPageList() {
        return webPageList;
    }

    public void setWebPageList(List<WebPage> webPageList) {
        this.webPageList = webPageList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
