package com.flightfinder.client.model.browsedates;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "CarrierIds",
        "OriginId",
        "DestinationId",
        "DepartureDate"
})
public class InboundLeg {

    @JsonProperty("CarrierIds")
    private List<Long> carrierIds = null;
    @JsonProperty("OriginId")
    private Long originId;
    @JsonProperty("DestinationId")
    private Long destinationId;
    @JsonProperty("DepartureDate")
    private String departureDate;

    @JsonProperty("CarrierIds")
    public List<Long> getCarrierIds() {
        return carrierIds;
    }

    @JsonProperty("CarrierIds")
    public void setCarrierIds(List<Long> carrierIds) {
        this.carrierIds = carrierIds;
    }

    @JsonProperty("OriginId")
    public Long getOriginId() {
        return originId;
    }

    @JsonProperty("OriginId")
    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    @JsonProperty("DestinationId")
    public Long getDestinationId() {
        return destinationId;
    }

    @JsonProperty("DestinationId")
    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    @JsonProperty("DepartureDate")
    public String getDepartureDate() {
        return departureDate;
    }

    @JsonProperty("DepartureDate")
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

}
