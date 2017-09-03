package app.dao;

import app.model.WebPage;

import java.util.List;

public interface WebPageDao {

    void saveOrUpdate(WebPage webPage);
    List<WebPage> fullTextSearch(String text);

}
