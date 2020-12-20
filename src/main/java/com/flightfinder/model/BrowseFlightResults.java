package com.flightfinder.model;

import java.util.ArrayList;
import java.util.List;

public class BrowseFlightResults {
    private Long quoteID;
    private Double minPrice;
    private Boolean direct;
    private String currency;
    private List<String> airlines = new ArrayList<>();

    public BrowseFlightResults() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<String> airlines) {
        this.airlines = airlines;
    }

    public Long getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(Long quoteID) {
        this.quoteID = quoteID;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Boolean getDirect() {
        return direct;
    }

    public void setDirect(Boolean direct) {
        this.direct = direct;
    }

    public void setFlightNames(List<String> flightNames) {
        this.airlines = flightNames;
    }

    @Override
    public String toString() {
        return "BrowseFlightResults{" +
                "quoteID=" + quoteID +
                ", minPrice= $ " + minPrice +
                ", direct=" + direct +
                ", airlines=" + airlines +
                '}';
    }
}
