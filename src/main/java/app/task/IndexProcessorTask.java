package app.task;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Scope("prototype")
public class IndexProcessorTask implements Runnable {

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
    }

    }

}
