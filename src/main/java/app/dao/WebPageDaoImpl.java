package app.dao;

import app.sql.mapper.CountMapper;
import app.sql.mapper.WebPageRowMapper;
import app.sql.SQLQuery;
import app.sql.builder.WebPageSqlQueryBuilder;
import app.model.WebPage;
import app.sql.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import java.util.logging.Logger;

@Repository
@Qualifier("webPageDao")
public class WebPageDaoImpl implements WebPageDao {

    @Autowired
    private WebPageSqlQueryBuilder webPageSqlQueryBuilder;

    private JdbcTemplate jdbcTemplate;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public WebPageDaoImpl(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public synchronized void saveOrUpdate(WebPage webPage) {
        SQLQuery query = webPageSqlQueryBuilder.saveOrUpdate(webPage);
        try {
            // TODO find solution for saving utf8mb4 text in db
            jdbcTemplate.update(query.getQuery(), query.getArgs());
        } catch (UncategorizedSQLException e) {
            logger.warning("Page from " + webPage.getUrl() + " url not saved in db. Throwed UncategorizedSQLException");
        }
    }

    @Override
    public SearchResult fullTextSearch(String searchText, int startResultNumber) {
        List<WebPage> webPageList = fullTextSearchResult(searchText, startResultNumber);
        int resultCount = fullTextSearchResultCount(searchText);

        return new SearchResult(searchText, webPageList, resultCount);
    }

    private List<WebPage> fullTextSearchResult(String searchText, int startResultNumber) {
        SQLQuery query = webPageSqlQueryBuilder.fullTextSearch(searchText, startResultNumber);

        return jdbcTemplate.query(query.getQuery(), query.getArgs(), new WebPageRowMapper());
    }

    private int fullTextSearchResultCount(String searchText) {
        SQLQuery query = webPageSqlQueryBuilder.fullTextSearchCount(searchText);

        return jdbcTemplate.queryForObject(query.getQuery(), query.getArgs(), new CountMapper());
    }

}
