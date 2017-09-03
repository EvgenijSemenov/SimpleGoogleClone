package app.dao;

import app.model.WebPage;

import java.util.List;

public interface WebPageDAO {

    void saveOrUpdate(WebPage webPage);
    List<WebPage> fullTextSearch(String text);

}
