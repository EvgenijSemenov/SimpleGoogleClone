package app.task;

import app.dao.WebPageDaoImpl;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Scope("prototype")
public class IndexTask implements Runnable {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private WebPageDaoImpl webPageDao;

    private String url;
    private volatile Set<String> urls;

    @Override
    public void run() {
        Response response = loadDocument(url);

        if (iSuccessResponse(response)) {
            Document document = Jsoup.parse(response.body());
            webPageDao.saveOrUpdate(getWebPageFromDocument(document));
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

    }

}
