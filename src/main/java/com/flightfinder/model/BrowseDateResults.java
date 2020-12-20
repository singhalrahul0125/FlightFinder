package com.flightfinder.model;

import java.util.ArrayList;
import java.util.List;

public class BrowseDateResults {

    private Long quoteID;
    private Double minPrice;
    private String currency;
    private Boolean direct;
    private String departureDate;
    private List<String> airlines = new ArrayList<>();

    public BrowseDateResults() {
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public List<String> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<String> airlines) {
        this.airlines = airlines;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
