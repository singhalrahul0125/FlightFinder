package com.flightfinder.client.model.browseflights;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "QuoteId",
        "MinPrice",
        "Direct",
        "OutboundLeg",
        "QuoteDateTime",
        "InboundLeg"
})
public class Quote {

    @JsonProperty("QuoteId")
    private Long quoteId;
    @JsonProperty("MinPrice")
    private Double minPrice;
    @JsonProperty("Direct")
    private Boolean direct;
    @JsonProperty("OutboundLeg")
    private OutboundLeg outboundLeg;
    @JsonProperty("QuoteDateTime")
    private String quoteDateTime;
    @JsonProperty("InboundLeg")
    private InboundLeg inboundLeg;

    @JsonProperty("QuoteId")
    public Long getQuoteId() {
        return quoteId;
    }

    @JsonProperty("QuoteId")
    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    @JsonProperty("MinPrice")
    public Double getMinPrice() {
        return minPrice;
    }

    @JsonProperty("MinPrice")
    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    @JsonProperty("Direct")
    public Boolean getDirect() {
        return direct;
    }

    @JsonProperty("Direct")
    public void setDirect(Boolean direct) {
        this.direct = direct;
    }

    @JsonProperty("OutboundLeg")
    public OutboundLeg getOutboundLeg() {
        return outboundLeg;
    }

    @JsonProperty("OutboundLeg")
    public void setOutboundLeg(OutboundLeg outboundLeg) {
        this.outboundLeg = outboundLeg;
    }

    @JsonProperty("QuoteDateTime")
    public String getQuoteDateTime() {
        return quoteDateTime;
    }

    @JsonProperty("QuoteDateTime")
    public void setQuoteDateTime(String quoteDateTime) {
        this.quoteDateTime = quoteDateTime;
    }

    @JsonProperty("InboundLeg")
    public InboundLeg getInboundLeg() {
        return inboundLeg;
    }

    @JsonProperty("InboundLeg")
    public void setInboundLeg(InboundLeg inboundLeg) {
        this.inboundLeg = inboundLeg;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteId=" + quoteId +
                ", minPrice=" + minPrice +
                ", direct=" + direct +
                ", outboundLeg=" + outboundLeg +
                ", quoteDateTime='" + quoteDateTime + '\'' +
                ", inboundLeg=" + inboundLeg +
                '}';
    }
}
