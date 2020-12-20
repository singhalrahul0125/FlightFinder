package com.flightfinder.service;

import com.flightfinder.model.BrowseDateResults;
import com.flightfinder.persistance.model.UserData;
import com.flightfinder.persistance.repository.UserDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Configuration
@EnableScheduling
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    FlightFinderService flightFinderService;

    @Autowired
    EmailService emailService;

    @Scheduled(fixedRate = 86400000)
    public void sendEmail() {
        LocalDate oneMonthFromNow = LocalDate.now().plusMonths(1);
        for (UserData userdata : userDataRepository.findAll()) {
            String origin = userdata.getOrigin();
            String destination = userdata.getDestination();
            Double price = userdata.getPrice();
            for (BrowseDateResults browseDateResults : flightFinderService.browseDates(origin, destination, "anytime"
                    , "anytime")) {
                if (browseDateResults.getMinPrice() <= price) {
                    LocalDate departureDate = convertToLocalDate(browseDateResults.getDepartureDate());
                    if (departureDate.isBefore(oneMonthFromNow)) {
                        emailService.sendEmail(userdata.getEmail(), userdata.getOrigin(), userdata.getDestination(),
                                browseDateResults.getMinPrice(), browseDateResults.getCurrency(),
                                browseDateResults.getDepartureDate(), browseDateResults.getAirlines().get(0));
                    }
                }
            }
        }
    }

    private LocalDate convertToLocalDate(final String departureDate) {
        try {
            Date date = DATE_FORMAT.parse(departureDate);
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } catch (ParseException e) {
            logger.error("Unable to parse date " + departureDate, e);
            return LocalDate.now();
        }
    }
}
