package app.dao;

import app.model.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Component
public class WebPageDaoImpl implements WebPageDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public WebPageDaoImpl(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public synchronized void saveOrUpdate(WebPage webPage) {
    }

    @Override
    public List<WebPage> fullTextSearch(String text) {
        return null;
    }

}
