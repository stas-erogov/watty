package ru.erogov.watty.api;

public class Message {
    public static final String CLASS_NAME_VARIABLE="type";

    private String query;

    public Message() {
        this.type = this.getClass().getName();
    }

    private String type;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
