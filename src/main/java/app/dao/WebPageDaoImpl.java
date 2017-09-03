package app.dao;

import app.model.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.List;

import java.util.logging.Logger;

@Component
public class WebPageDaoImpl implements WebPageDAO {

    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public WebPageDaoImpl(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public synchronized void saveOrUpdate(WebPage webPage) {
        String sql = "REPLACE INTO index_page (url, title, text) VALUES (?, ?, ?)";
        try {
            // TODO find solution for saving utf8mb4 text in db
            jdbcTemplate.update(sql, webPage.getUrl(), webPage.getTitle(), webPage.getText());
        } catch (UncategorizedSQLException e) {
            logger.warning("Page from " + webPage.getUrl() + " url not saved in db. Throwed UncategorizedSQLException");
        }
    }

    @Override
    public List<WebPage> fullTextSearch(String text) {
        return null;
    }

}