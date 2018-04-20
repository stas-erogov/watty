package ru.erogov.watty.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeMark {
    @JsonProperty("trademark_id")
    private long trademarkId;

    @JsonProperty("trademark_name")
    private String tradeMarkName;

    private TradeMark() {
    }

    public TradeMark(String tradeMarkName) {
        this.tradeMarkName = tradeMarkName;
    }

    public long getTrademarkId() {
        return trademarkId;
    }

    public String getTradeMarkName() {
        return tradeMarkName;
    }
}
