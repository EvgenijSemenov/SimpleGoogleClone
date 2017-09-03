package app.service;

import app.task.IndexProcessorTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class WebPageServiceImpl implements WebPageService {

    @Autowired
    TaskExecutor taskExecutor;

    @Autowired
    IndexProcessorTask indexProcessorTask;

    @Override
    public String indexByUrl(String indexUrl, int maxUrlsSearchDeep) {
        taskExecutor.execute(indexProcessorTask);

        return null;
    }

}
