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

        if (iSuccessResponse(response)) {
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebPage getWebPage() {
        return webPage;
    }

    public void setWebPage(WebPage webPage) {
        this.webPage = webPage;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    private boolean iSuccessResponse(Response response) {
        return Objects.nonNull(response) && response.statusCode() == 200;
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
