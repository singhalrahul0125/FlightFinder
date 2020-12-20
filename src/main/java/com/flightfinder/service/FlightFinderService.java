package com.flightfinder.service;

import com.flightfinder.client.model.browseflights.ClientResponse;
import com.flightfinder.client.model.browseflights.Quote;
import com.flightfinder.model.BrowseDateResults;
import com.flightfinder.model.BrowseFlightResults;
import com.flightfinder.persistance.model.UserData;
import com.flightfinder.persistance.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightFinderService {

    private final RestTemplate restTemplate;
    private final UserDataRepository userDataRepository;
    private final String tempUrl;
    private final String rapidAPIKey;

    @Autowired
    public FlightFinderService(RestTemplate restTemplate, UserDataRepository userDataRepository, @Value("$" +
            "{FlightFinderService.tempUrl}") String
            tempUrl, @Value("${FlightFinderService.rapidAPIKey}") String rapidAPIKey) {
        this.restTemplate = restTemplate;
        this.userDataRepository = userDataRepository;
        this.tempUrl = tempUrl;
        this.rapidAPIKey = rapidAPIKey;
    }

    public List<BrowseFlightResults> browseFlights(String origin, String destination,
                                                   String outboundDate, String inboundDate) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidAPIKey);
        HttpEntity request = new HttpEntity(headers);

        String url = generateUrl(tempUrl, origin, destination, outboundDate, inboundDate);
        ResponseEntity<ClientResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request,
                ClientResponse.class);
        ClientResponse response = responseEntity.getBody();

        Map<Long, String> carrierMap = new HashMap<>();
        response.getCarriers().forEach(e -> carrierMap.put(e.getCarrierId(), e.getName()));

        return response.getQuotes()
                .stream()
                .map(e -> populateBrowseFlightResults(carrierMap, e))
                .collect(Collectors.toList());
    }

    public List<BrowseDateResults> browseDates(final String originPlace, final String destinationPlace,
                                               final String outboundDate, final String inboundDate) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidAPIKey);
        HttpEntity request = new HttpEntity(headers);

        String url = generateUrl(tempUrl, originPlace, destinationPlace, outboundDate, inboundDate);
        ResponseEntity<ClientResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request,
                ClientResponse.class);
        ClientResponse response = responseEntity.getBody();

        Map<Long, String> carrierMap = new HashMap<>();
        response.getCarriers().forEach(e -> carrierMap.put(e.getCarrierId(), e.getName()));

        return response.getQuotes()
                .stream()
                .map(e -> populateBrowseDateResults(carrierMap, e))
                .collect(Collectors.toList());
    }

    @Transactional
    public void subscribe(final String origin, final String destination, final Double price, final String email) {
        UserData userData = new UserData();
        userData.setEmail(email);
        userData.setPrice(price);
        userData.setDestination(destination);
        userData.setOrigin(origin);
        userDataRepository.save(userData);
    }

    @Transactional
    public void unsubscribe(final String email) {
        long noOfRecordsDelete = userDataRepository.deleteByEmail(email);
        if (noOfRecordsDelete == 0) {
            throw new IllegalArgumentException("Email id does not exist!");
        }
    }

    @Transactional
    public void unsubscribe(final String origin, final String destination, final String email) {
        long noOfRecordsDelete = userDataRepository.deleteByEmailAndOriginAndDestination(email, origin, destination);
        if (noOfRecordsDelete == 0) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
    }

    private BrowseFlightResults populateBrowseFlightResults(Map<Long, String> carrierMap, Quote quote) {
        BrowseFlightResults browseFlightResults = new BrowseFlightResults();
        browseFlightResults.setMinPrice(quote.getMinPrice());
        browseFlightResults.setDirect(quote.getDirect());
        browseFlightResults.setQuoteID(quote.getQuoteId());
        browseFlightResults.setCurrency("$");
        for (Long carrierId : quote.getOutboundLeg().getCarrierIds()) {
            browseFlightResults.getAirlines().add(carrierMap.get(carrierId));
        }
        return browseFlightResults;
    }

    private BrowseDateResults populateBrowseDateResults(Map<Long, String> carrierMap, Quote quote) {
        BrowseDateResults browseDateResults = new BrowseDateResults();
        browseDateResults.setQuoteID(quote.getQuoteId());
        browseDateResults.setDirect(quote.getDirect());
        browseDateResults.setMinPrice(quote.getMinPrice());
        browseDateResults.setDepartureDate(quote.getOutboundLeg().getDepartureDate());
        browseDateResults.setCurrency("$");
        for (Long carrierId : quote.getOutboundLeg().getCarrierIds()) {
            browseDateResults.getAirlines().add(carrierMap.get(carrierId));
        }
        return browseDateResults;
    }

    private String generateUrl(final String tempUrl, final String originPlace, final String destinationPlace,
                              final String outboundDate, final String inboundDate) {
        return tempUrl + originPlace + "/" + destinationPlace + "/" + outboundDate + "?inboundpartialdate=" + inboundDate;
    }
}


