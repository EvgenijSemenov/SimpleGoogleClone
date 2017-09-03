package app.service;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class WebPageServiceImpl implements WebPageService {

    @Autowired
    TaskExecutor taskExecutor;

    @Override
    public String indexByUrl(String indexUrl, int maxUrlsSearchDeep) {
        return null;
    }

}
