package app.service;

import app.sql.model.SearchResult;

public interface WebPageService {

    String indexByUrl(String indexUrl, int maxUrlsSearchDeep);
    SearchResult fulltextSearch(String searchText, int startResultNumber);

}
