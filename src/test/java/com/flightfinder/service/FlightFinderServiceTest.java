package com.flightfinder.service;

import com.flightfinder.client.model.browseflights.Carrier;
import com.flightfinder.client.model.browseflights.ClientResponse;
import com.flightfinder.client.model.browseflights.OutboundLeg;
import com.flightfinder.client.model.browseflights.Quote;
import com.flightfinder.model.BrowseDateResults;
import com.flightfinder.model.BrowseFlightResults;
import com.flightfinder.persistance.model.UserData;
import com.flightfinder.persistance.repository.UserDataRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightFinderServiceTest {

    final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    final UserDataRepository userDataRepository = Mockito.mock(UserDataRepository.class);

    final FlightFinderService flightFinderService = new FlightFinderService(restTemplate, userDataRepository,
            "url", "key");


    @Test
    public void testBrowseFlightsGivenOriginDestinationOutboundDateThenReturnFlights() {
        Carrier carrier = new Carrier();
        carrier.setCarrierId(1L);
        carrier.setName("Airlines");
        List<Carrier> listOfCarriers = Arrays.asList(carrier);

        OutboundLeg outboundLeg = new OutboundLeg();
        outboundLeg.setCarrierIds(Arrays.asList(1L));

        Quote quote = new Quote();
        quote.setDirect(false);
        quote.setMinPrice(200.0);
        quote.setQuoteId(1L);
        quote.setQuoteDateTime("datetime");
        quote.setOutboundLeg(outboundLeg);
        List<Quote> listOfQuotes = Arrays.asList(quote);

        ResponseEntity<ClientResponse> responseEntity = Mockito.mock(ResponseEntity.class);
        ClientResponse response = new ClientResponse();
        response.setCarriers(listOfCarriers);
        response.setQuotes(listOfQuotes);
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class),
                Mockito.any(HttpEntity.class), Mockito.any(Class.class))).thenReturn(responseEntity);
        Mockito.when(responseEntity.getBody()).thenReturn(response);

        List<BrowseFlightResults> flightResults = flightFinderService.browseFlights("DEL", "MUC", "2020-12-20",
                "anytime");

        assertThat(flightResults.get(0).getMinPrice()).isEqualTo(quote.getMinPrice());
    }

    @Test
    public void testBrowseDatesGivenOriginDestinationReturnFlightsAndDates() {
        Carrier carrier = new Carrier();
        carrier.setCarrierId(1L);
        carrier.setName("Airlines");
        List<Carrier> listOfCarriers = Arrays.asList(carrier);

        OutboundLeg outboundLeg = new OutboundLeg();
        outboundLeg.setCarrierIds(Arrays.asList(1L));

        Quote quote = new Quote();
        quote.setDirect(false);
        quote.setMinPrice(200.0);
        quote.setQuoteId(1L);
        quote.setQuoteDateTime("datetime");
        quote.setOutboundLeg(outboundLeg);
        List<Quote> listOfQuotes = Arrays.asList(quote);

        ResponseEntity<ClientResponse> responseEntity = Mockito.mock(ResponseEntity.class);
        ClientResponse response = new ClientResponse();
        response.setCarriers(listOfCarriers);
        response.setQuotes(listOfQuotes);
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class),
                Mockito.any(HttpEntity.class), Mockito.any(Class.class))).thenReturn(responseEntity);
        Mockito.when(responseEntity.getBody()).thenReturn(response);

        List<BrowseDateResults> flightResults = flightFinderService.browseDates("DEL", "MUC", "anytime", "anytime");

        assertThat(flightResults.get(0).getMinPrice()).isEqualTo(quote.getMinPrice());
    }

    @Test
    public void testSubscribeGivenUserDetailsSaveDataInDataBase() {
        final ArgumentCaptor<UserData> captor = ArgumentCaptor.forClass(UserData.class);
        UserData userData = new UserData();
        Mockito.when(userDataRepository.save(Mockito.any())).thenReturn(userData);
        flightFinderService.subscribe("origin", "destination", 10.0, "email");
        Mockito.verify(userDataRepository).save(captor.capture());
        UserData actualUserData = captor.getValue();
        assertThat(actualUserData.getOrigin()).isEqualTo("origin");
    }

    @Test
    public void testUnsubscribeGivenEmailIdDeleteUserDataFromDataBase() {
        Mockito.when(userDataRepository.deleteByEmail("email")).thenReturn(1L);
        flightFinderService.unsubscribe("email");
        Mockito.verify(userDataRepository).deleteByEmail("email");
    }

    @Test
    public void testUnsubscribeGivenInvalidEmailThrowException() {
        Mockito.when(userDataRepository.deleteByEmail("invalidEmail")).thenReturn(0L);
        try {
            flightFinderService.unsubscribe("invalidEmail");
            Assertions.failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Email id does not exist!");
        }
    }

    @Test
    public void testUnsubscribeGivenEmailIdOriginDestinationDeleteUserDataFromDataBase() {
        Mockito.when(userDataRepository.deleteByEmailAndOriginAndDestination("email", "origin", "destination")).thenReturn(1L);
        flightFinderService.unsubscribe("origin", "destination", "email");
        Mockito.verify(userDataRepository).deleteByEmailAndOriginAndDestination("email", "origin", "destination");
    }

    @Test
    public void testUnsubscribeGivenInvalidParametersThrowException() {
        Mockito.when(userDataRepository.deleteByEmailAndOriginAndDestination("invalidEmail", "invalidOrigin",
                "invalidDestination")).thenReturn(0L);
        try {
            flightFinderService.unsubscribe("invalidEmail", "invalidOrigin", "invalidDestination");
            Assertions.failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Invalid input parameters");
        }
    }


}
