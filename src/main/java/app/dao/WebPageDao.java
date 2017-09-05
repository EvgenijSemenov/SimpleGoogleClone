package app.dao;

import app.model.WebPage;
import app.sql.model.SearchResult;

import java.util.List;

public interface WebPageDao {

    void saveOrUpdate(WebPage webPage);
    SearchResult fullTextSearch(String searchText, int startResultNumber);

}
