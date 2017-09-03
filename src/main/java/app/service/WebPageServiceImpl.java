package app.service;

import app.dao.WebPageDao;
import app.model.WebPage;
import app.task.IndexProcessorTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("webPageService")
public class WebPageServiceImpl implements WebPageService {

    @Autowired
    TaskExecutor taskExecutor;

    @Autowired
    IndexProcessorTask indexProcessorTask;

    @Autowired
    WebPageDao webPageDao;

    @Override
    public String indexByUrl(String indexUrl, int maxUrlsSearchDeep) {
        Set<String> urls = new HashSet<>();

        if (isUrlHasHttpPrefix(indexUrl)) {
            indexUrl = "http://" + indexUrl;
        }

        urls.add(indexUrl);

        indexProcessorTask.setUrls(urls);
        indexProcessorTask.setMaxSearchUrlDeep(maxUrlsSearchDeep);

        taskExecutor.execute(indexProcessorTask);

        return null;
    }

    @Override
    public List<WebPage> fulltextSearch(String text) {
        return webPageDao.fullTextSearch(text);
    }

    private boolean isUrlHasHttpPrefix(String url) {
        return !(url.startsWith("http://") && url.startsWith("https://"));
    }
}
