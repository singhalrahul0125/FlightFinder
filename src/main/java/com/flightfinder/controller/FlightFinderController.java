package com.flightfinder.controller;

import com.flightfinder.model.BrowseDateResults;
import com.flightfinder.model.BrowseFlightResults;
import com.flightfinder.service.FlightFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("flightfinder")
public class FlightFinderController {

    @Autowired
    private FlightFinderService flightFinderService;

    @GetMapping("/browseflights/{origin}/{destination}/{outboundDate}")
    public List<BrowseFlightResults> browseFlights(@PathVariable final String origin, @PathVariable final String
            destination, @PathVariable final String outboundDate, @RequestParam(value = "returndate", required = false,
            defaultValue = "anytime") final String inbounddate) {

        return flightFinderService.browseFlights(origin.toUpperCase(), destination.toUpperCase(), outboundDate,
                inbounddate);
    }

    @GetMapping("/browsedates/{origin}/{destination}")
    public List<BrowseDateResults> browseDates(@PathVariable final String origin,
                                               @PathVariable final String destination,
                                               @RequestParam(value = "outbounddate", required = false,
                                                       defaultValue = "anytime") final String outbounddate,
                                               @RequestParam(value = "returndate", required = false,
                                                       defaultValue = "anytime") final String inbounddate) {

        return flightFinderService.browseDates(origin.toUpperCase(), destination.toUpperCase(), outbounddate,
                inbounddate);

    }

    @PostMapping("/subscribe/{origin}/{destination}/{price}")
    public String subscribe(@PathVariable final String origin, @PathVariable final String destination,
                            @PathVariable final Double price,
                            @RequestBody final Map<String, String> requestEmail) {
        flightFinderService.subscribe(origin.toUpperCase(), destination.toUpperCase(), price, requestEmail.get("email"
        ));
        return "Email and preferences have been saved.";
    }

    @PostMapping("/unsubscribe")
    public String unsubscribe(@RequestBody final Map<String, String> requestEmail) {
        flightFinderService.unsubscribe(requestEmail.get("email"));
        return "Email has been successfully unsubscribed.";
    }

    @PostMapping("/unsubscribe/{origin}/{destination}")
    public String unsubscribe(@PathVariable final String origin, @PathVariable final String destination,
                              @RequestBody final Map<String, String> requestEmail) {
        flightFinderService.unsubscribe(origin.toUpperCase(), destination.toUpperCase(), requestEmail.get(
                "email"));
        return "Email has been successfully unsubscribed.";
    }
}
