package app.sql.builder;

import app.model.WebPage;
import app.sql.SQLQuery;
import org.springframework.stereotype.Component;

@Component
public class WebPageSqlQueryBuilder {

    private final String TABLE = "index_page";
    private final String ID_COLUMN = "id";
    private final String URL_COLUMN = "url";
    private final String TITLE_COLUMN = "title";
    private final String TEXT_COLUMN = "text";

    public SQLQuery saveOrUpdate(WebPage webPage) {
        String query = "INSERT INTO `" + TABLE + "` (`" + ID_COLUMN +"`, `" + URL_COLUMN +"`, `" +
                TITLE_COLUMN + "`, `" + TEXT_COLUMN + "`) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE `" + TITLE_COLUMN + "`=?, `" + TEXT_COLUMN + "`=?";
        Object[] args = new Object[] { webPage.getId(), webPage.getUrl(), webPage.getTitle(), webPage.getText(),
                webPage.getTitle(), webPage.getText() };

        return new SQLQuery(query, args);
    }

}
