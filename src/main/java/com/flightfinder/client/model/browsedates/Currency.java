package com.flightfinder.client.model.browsedates;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Code",
        "Symbol",
        "ThousandsSeparator",
        "DecimalSeparator",
        "SymbolOnLeft",
        "SpaceBetweenAmountAndSymbol",
        "RoundingCoefficient",
        "DecimalDigits"
})
public class Currency {

    @JsonProperty("Code")
    private String code;
    @JsonProperty("Symbol")
    private String symbol;
    @JsonProperty("ThousandsSeparator")
    private String thousandsSeparator;
    @JsonProperty("DecimalSeparator")
    private String decimalSeparator;
    @JsonProperty("SymbolOnLeft")
    private Boolean symbolOnLeft;
    @JsonProperty("SpaceBetweenAmountAndSymbol")
    private Boolean spaceBetweenAmountAndSymbol;
    @JsonProperty("RoundingCoefficient")
    private Long roundingCoefficient;
    @JsonProperty("DecimalDigits")
    private Long decimalDigits;

    @JsonProperty("Code")
    public String getCode() {
        return code;
    }

    @JsonProperty("Code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("Symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("Symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("ThousandsSeparator")
    public String getThousandsSeparator() {
        return thousandsSeparator;
    }

    @JsonProperty("ThousandsSeparator")
    public void setThousandsSeparator(String thousandsSeparator) {
        this.thousandsSeparator = thousandsSeparator;
    }

    @JsonProperty("DecimalSeparator")
    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    @JsonProperty("DecimalSeparator")
    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    @JsonProperty("SymbolOnLeft")
    public Boolean getSymbolOnLeft() {
        return symbolOnLeft;
    }

    @JsonProperty("SymbolOnLeft")
    public void setSymbolOnLeft(Boolean symbolOnLeft) {
        this.symbolOnLeft = symbolOnLeft;
    }

    @JsonProperty("SpaceBetweenAmountAndSymbol")
    public Boolean getSpaceBetweenAmountAndSymbol() {
        return spaceBetweenAmountAndSymbol;
    }

    @JsonProperty("SpaceBetweenAmountAndSymbol")
    public void setSpaceBetweenAmountAndSymbol(Boolean spaceBetweenAmountAndSymbol) {
        this.spaceBetweenAmountAndSymbol = spaceBetweenAmountAndSymbol;
    }

    @JsonProperty("RoundingCoefficient")
    public Long getRoundingCoefficient() {
        return roundingCoefficient;
    }

    @JsonProperty("RoundingCoefficient")
    public void setRoundingCoefficient(Long roundingCoefficient) {
        this.roundingCoefficient = roundingCoefficient;
    }

    @JsonProperty("DecimalDigits")
    public Long getDecimalDigits() {
        return decimalDigits;
    }

    @JsonProperty("DecimalDigits")
    public void setDecimalDigits(Long decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

}
