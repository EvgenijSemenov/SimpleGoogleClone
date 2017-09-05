package app.service;

import app.dao.WebPageDao;
import app.sql.model.SearchResult;
import app.task.IndexProcessorTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("webPageService")
public class WebPageServiceImpl implements WebPageService {

    @Autowired
    private ThreadPoolTaskExecutor indexProcessorTaskExecutor;

    @Autowired
    private WebPageDao webPageDao;

    @Autowired
    private ApplicationContext appContext;

    @Value( "${max.index.processor.task.thread.count}" )
    private int maxIndexProcessorTaskThreadCount;


    @Override
    public String indexByUrl(String indexUrl, int maxUrlsSearchDeep) {
        String resultMessage = null;
        if(isMaxIndexProcessorThreadRun(indexProcessorTaskExecutor)) {
            resultMessage = "All index thread busy now. Url added in index queue.";
        } else {
            resultMessage = "Url index started.";
        }

        executeIndexProcessorTask(indexUrl, maxUrlsSearchDeep);

        return resultMessage;
    }

    @Override
    public SearchResult fulltextSearch(String searchText, int startResultNumber) {
        return webPageDao.fullTextSearch(searchText, startResultNumber);
    }

    private boolean isMaxIndexProcessorThreadRun(ThreadPoolTaskExecutor indexProcessorTaskExecutor) {
        return indexProcessorTaskExecutor.getActiveCount() >= maxIndexProcessorTaskThreadCount;
    }

    private void executeIndexProcessorTask(String indexUrl, int maxUrlsSearchDeep) {
        indexProcessorTaskExecutor.execute(newIndexProcessorTaskInstance(indexUrl, maxUrlsSearchDeep));
    }

    private boolean isUrlHasHttpPrefix(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    private IndexProcessorTask newIndexProcessorTaskInstance(String url, int maxUrlsSearchDeep) {
        Set<String> urls = new HashSet<>();

        if (!isUrlHasHttpPrefix(url)) {
            url = "http://" + url;
        }
        urls.add(url);

        IndexProcessorTask indexProcessorTask = (IndexProcessorTask) appContext.getBean("indexProcessorTask");
        indexProcessorTask.setUrls(urls);
        indexProcessorTask.setMaxSearchUrlDeep(maxUrlsSearchDeep);

        return indexProcessorTask;
    }

}
