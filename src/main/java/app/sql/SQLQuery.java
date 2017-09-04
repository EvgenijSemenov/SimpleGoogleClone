package app.sql;

public class SQLQuery {

    private String query;
    private Object[] args;

    public SQLQuery(String query, Object[] args) {
        this.query = query;
        this.args = args;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

}
