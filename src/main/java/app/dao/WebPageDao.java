package app.dao;

import app.model.WebPage;
import app.sql.model.SearchResult;

public interface WebPageDao {

    void saveOrUpdate(WebPage webPage);
    SearchResult fullTextSearch(String searchText, int startResultNumber);

}
