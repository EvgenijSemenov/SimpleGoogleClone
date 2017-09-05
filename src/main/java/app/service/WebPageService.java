package app.service;

import app.model.WebPage;
import app.sql.model.SearchResult;

import java.util.List;
import java.util.Set;

public interface WebPageService {

    String indexByUrl(String indexUrl, int maxUrlsSearchDeep);
    SearchResult fulltextSearch(String searchText, int startResultNumber);

}
