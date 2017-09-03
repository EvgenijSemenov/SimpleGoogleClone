package app.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class IndexProcessorTask implements Runnable {

    @Override
    public void run() {
    }

}
