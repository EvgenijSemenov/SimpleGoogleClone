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

    @Value( "${max.index.processor.task.thread.count}" )
    private int maxIndexProcessorTaskThreadCount;

    @Bean
    public ThreadPoolTaskExecutor indexTaskExecutor() {
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(maxIndexTaskThreadCount);
        t.setMaxPoolSize(100);
        t.afterPropertiesSet();
        return t;
    }

    @Bean
    public ThreadPoolTaskExecutor indexProcessorTaskExecutor() {
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(maxIndexProcessorTaskThreadCount);
        t.setMaxPoolSize(100);
        t.afterPropertiesSet();
        return t;
    }

}
