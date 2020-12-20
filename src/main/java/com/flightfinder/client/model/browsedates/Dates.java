package com.flightfinder.client.model.browsedates;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "OutboundDates",
        "InboundDates"
})
public class Dates {

    @JsonProperty("OutboundDates")
    private List<OutboundDate> outboundDates = null;
    @JsonProperty("InboundDates")
    private List<InboundDate> inboundDates = null;

    @JsonProperty("OutboundDates")
    public List<OutboundDate> getOutboundDates() {
        return outboundDates;
    }

    @JsonProperty("OutboundDates")
    public void setOutboundDates(List<OutboundDate> outboundDates) {
        this.outboundDates = outboundDates;
    }

    @JsonProperty("InboundDates")
    public List<InboundDate> getInboundDates() {
        return inboundDates;
    }

    @JsonProperty("InboundDates")
    public void setInboundDates(List<InboundDate> inboundDates) {
        this.inboundDates = inboundDates;
    }

}
