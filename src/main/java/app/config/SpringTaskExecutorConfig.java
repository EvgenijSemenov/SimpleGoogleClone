package app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringTaskExecutorConfig {

    @Value( "${max.index.task.thread.count}" )
    private int maxIndexTaskThreadCount;

    @Bean
    public TaskExecutor indexTaskExecutor() {
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(maxIndexTaskThreadCount);
        t.setMaxPoolSize(100);
        t.afterPropertiesSet();
        return t;
    }

}
