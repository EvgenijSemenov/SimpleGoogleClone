package app.task;

import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class IndexTask implements Runnable {

    @Override
    public void run() {
        Response response = loadDocument(url);
    }

    private Response loadDocument(String url) {
        Response response = null;

        try {
            response = Jsoup.connect(url).execute();
        } catch (HttpStatusException e) {
            logger.info(url);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    }

}
