package app.task;

import app.dao.WebPageDao;
import app.model.WebPage;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

@Component
@Scope("prototype")
public class IndexTask implements Runnable {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private WebPageDao webPageDao;

    private String url;
    private volatile Set<String> urls;

    @Override
    public void run() {
        Response response = loadDocument(url);

        if (iSuccessResponse(response)) {
            Document document = Jsoup.parse(response.body());
            webPageDao.saveOrUpdate(getWebPageFromDocument(document));
            urls.addAll(getUrlsFromDocument(document));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    private WebPage getWebPageFromDocument(Document doc) {
        WebPage webPage = new WebPage();
        webPage.setUrl(url);
        webPage.setTitle(doc.title());
        webPage.setText(doc.text());

        return webPage;
    }

    private Set<String> getUrlsFromDocument(Document doc) {
        Set<String> urls = new HashSet<>();
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            if (link.attr("abs:href").startsWith("http://") || link.attr("abs:href").startsWith("https://"))
                urls.add(link.attr("abs:href"));
        }

        return urls;
    }

}
