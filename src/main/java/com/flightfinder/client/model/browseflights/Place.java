package com.flightfinder.client.model.browseflights;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "PlaceId",
        "IataCode",
        "Name",
        "Type",
        "SkyscannerCode",
        "CityName",
        "CityId",
        "CountryName"
})
public class Place {

    @JsonProperty("PlaceId")
    private Long placeId;
    @JsonProperty("IataCode")
    private String iataCode;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("SkyscannerCode")
    private String skyscannerCode;
    @JsonProperty("CityName")
    private String cityName;
    @JsonProperty("CityId")
    private String cityId;
    @JsonProperty("CountryName")
    private String countryName;

    @JsonProperty("PlaceId")
    public Long getPlaceId() {
        return placeId;
    }

    @JsonProperty("PlaceId")
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    @JsonProperty("IataCode")
    public String getIataCode() {
        return iataCode;
    }

    @JsonProperty("IataCode")
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("SkyscannerCode")
    public String getSkyscannerCode() {
        return skyscannerCode;
    }

    @JsonProperty("SkyscannerCode")
    public void setSkyscannerCode(String skyscannerCode) {
        this.skyscannerCode = skyscannerCode;
    }

    @JsonProperty("CityName")
    public String getCityName() {
        return cityName;
    }

    @JsonProperty("CityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @JsonProperty("CityId")
    public String getCityId() {
        return cityId;
    }

    @JsonProperty("CityId")
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @JsonProperty("CountryName")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("CountryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
