package app.service;

import app.dao.WebPageDaoImpl;
import app.model.WebPage;
import app.task.IndexProcessorTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WebPageServiceImpl implements WebPageService {

    @Autowired
    TaskExecutor taskExecutor;

    @Autowired
    IndexProcessorTask indexProcessorTask;

    @Autowired
    WebPageDaoImpl webPageDao;

    @Override
    public String indexByUrl(String indexUrl, int maxUrlsSearchDeep) {
        Set<String> urls = new HashSet<>();

        if (!indexUrl.startsWith("http://") && !indexUrl.startsWith("https://")) {
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

}
