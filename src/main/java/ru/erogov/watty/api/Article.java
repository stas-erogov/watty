package ru.erogov.watty.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
    @JsonProperty("article_id")
    private long articleId;

    @JsonProperty("article_code")
    private String articleCode;

    private String description;

    @JsonProperty("is_analog")
    private boolean isAnalog;

    @JsonProperty("trademark")
    private TradeMark tradeMark;

    private Article() {
    }

    public Article(String articleCode, String description, boolean isAnalog, TradeMark tradeMark) {
        this.articleCode = articleCode;
        this.description = description;
        this.isAnalog = isAnalog;
        this.tradeMark = tradeMark;
    }

    public long getArticleId() {
        return articleId;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAnalog() {
        return isAnalog;
    }

    public TradeMark getTradeMark() {
        return tradeMark;
    }
}
