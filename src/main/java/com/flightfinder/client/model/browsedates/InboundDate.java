package com.flightfinder.client.model.browsedates;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "PartialDate",
        "QuoteIds",
        "Price",
        "QuoteDateTime"
})
public class InboundDate {

    @JsonProperty("PartialDate")
    private String partialDate;
    @JsonProperty("QuoteIds")
    private List<Long> quoteIds = null;
    @JsonProperty("Price")
    private Double price;
    @JsonProperty("QuoteDateTime")
    private String quoteDateTime;

    @JsonProperty("PartialDate")
    public String getPartialDate() {
        return partialDate;
    }

    @JsonProperty("PartialDate")
    public void setPartialDate(String partialDate) {
        this.partialDate = partialDate;
    }

    @JsonProperty("QuoteIds")
    public List<Long> getQuoteIds() {
        return quoteIds;
    }

    @JsonProperty("QuoteIds")
    public void setQuoteIds(List<Long> quoteIds) {
        this.quoteIds = quoteIds;
    }

    @JsonProperty("Price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("Price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("QuoteDateTime")
    public String getQuoteDateTime() {
        return quoteDateTime;
    }

    @JsonProperty("QuoteDateTime")
    public void setQuoteDateTime(String quoteDateTime) {
        this.quoteDateTime = quoteDateTime;
    }

}
