package app.service;

import app.model.WebPage;

import java.util.List;
import java.util.Set;

public interface WebPageService {

    String indexByUrl(String indexUrl, int maxUrlsSearchDeep);
    List<WebPage> fulltextSearch(String text);

}
