package app.sql.mapper;

import app.model.WebPage;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WebPageRowMapper implements RowMapper<WebPage> {

    @Override
    public WebPage mapRow(ResultSet rs, int rowNum) throws SQLException {
        WebPage webPage = new WebPage();

        webPage.setId(rs.getLong("id"));
        webPage.setUrl(rs.getString("url"));
        webPage.setTitle(rs.getString("title"));
        webPage.setText(rs.getString("text"));

        return webPage;
    }

}
