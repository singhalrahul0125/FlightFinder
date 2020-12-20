package com.flightfinder.controller;

import com.flightfinder.controller.FlightFinderController;
import com.flightfinder.model.BrowseDateResults;
import com.flightfinder.model.BrowseFlightResults;
import com.flightfinder.service.FlightFinderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightFinderController.class)
public class FlightFinderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightFinderService flightFinderService;

    @Test
    public void testBrowseFlights() throws Exception {
        List<BrowseFlightResults> browseFlightResults = new ArrayList<>();
        when(flightFinderService.browseFlights("DEL", "MUC", "2020-10-25", "2020-10-30")).thenReturn(browseFlightResults);
        this.mockMvc.perform(get("/flightfinder/browseflights/DEL/MUC/2020-10-25")).andExpect(status().isOk());
    }

    @Test
    public void testBrowseDates() throws Exception {
        List<BrowseDateResults> browseDateResults = new ArrayList<>();
        when(flightFinderService.browseDates("DEL", "MUC", "anytime", "anytime")).thenReturn(browseDateResults);
        this.mockMvc.perform(get("/flightfinder/browsedates/DEL/MUC")).andExpect(status().isOk());
    }

    @Test
    public void testSubscribe() throws Exception {
        doNothing().when(flightFinderService).subscribe("DEL", "MUC", 600.0, "anyEmailId");
        this.mockMvc.perform(post("/flightfinder/subscribe/DEL/MUC/600")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"anyEmailId\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email and preferences have been saved."));

        Mockito.verify(flightFinderService).subscribe("DEL", "MUC", 600.0, "anyEmailId");
    }

    @Test
    public void testUnsubscribeWhenEmailIdIsCorrectThenReturnMessage() throws Exception {
        doNothing().when(flightFinderService).unsubscribe(anyString());
        this.mockMvc.perform(post("/flightfinder/unsubscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"anyEmailId\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email has been successfully unsubscribed."));
    }

    @Test
    public void testUnsubscribeWhenParametersAreCorrectThenReturnMessage() throws Exception {
        doNothing().when(flightFinderService).unsubscribe(anyString(), anyString(), anyString());
        this.mockMvc.perform(post("/flightfinder/unsubscribe/DEL/MUC")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"anyEmailId\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email has been successfully unsubscribed."));
    }
}
