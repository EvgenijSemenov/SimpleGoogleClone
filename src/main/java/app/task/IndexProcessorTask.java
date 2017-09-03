package app.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class IndexProcessorTask implements Runnable {

    private volatile Set<String> urls = new HashSet<>();
    private int maxSearchUrlDeep = 1;
    @Override
    public void run() {
    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public void setMaxSearchUrlDeep(int maxSearchUrlDeep) {
        this.maxSearchUrlDeep = maxSearchUrlDeep;
    }

    }

}
