package app.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

import java.util.logging.Logger;

@Component
@Scope("prototype")
public class IndexProcessorTask implements Runnable {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    private volatile Set<String> urls = new HashSet<>();
    private int maxSearchUrlDeep = 1;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void run() {
        indexProcess(maxSearchUrlDeep);
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public void setMaxSearchUrlDeep(int maxSearchUrlDeep) {
        this.maxSearchUrlDeep = maxSearchUrlDeep;
    }

    public void indexProcess(int maxSearchUrlDeep) {
        logger.info("-----------------------------------");
        logger.info("IndexProcessorTask start");
        taskExecutor.afterPropertiesSet();

        Set<String> callbackUrls = new HashSet<>();

        for (int urlsSearchDeep = 0; urlsSearchDeep < maxSearchUrlDeep; urlsSearchDeep++) {

            Set<String> indexUrls = getUrlsForIndex(urlsSearchDeep, callbackUrls);

            executeIndexTasks(indexUrls, callbackUrls);
        }
    }

    private Set<String> getUrlsForIndex(int urlSearchDeep, Set<String> callbackUrls) {
        Set<String> indexUrls = new HashSet<>();

        if (urlSearchDeep == 0) {
            indexUrls.addAll(urls);
        } else {
            indexUrls.addAll(excludeIndexedUrl(urls, callbackUrls));
        }

        return indexUrls;
    }

    private void executeIndexTasks(Set<String> indexUrls, Set<String> callbackUrls) {
        for (String url : indexUrls) {
            taskExecutor.execute(newIndexTaskInstance(url, callbackUrls));
        }
    }

    }

    private IndexTask newIndexTaskInstance(String url, Set<String> links) {
        IndexTask indexTask = (IndexTask) appContext.getBean("indexTask");
        indexTask.setUrl(url);
        indexTask.setUrls(links);

        return indexTask;
    }

}
