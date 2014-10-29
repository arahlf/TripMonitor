package com.arahlf.tripmonitor;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        Properties configProperties = PropertiesLoader.loadProperties("config/config.properties");
        
        String hotelName = configProperties.getProperty("hotelName");
        String departureAirportCode = configProperties.getProperty("departureAirportCode");
        String destinationAirportCode = configProperties.getProperty("destinationAirportCode");
        
        DateFormat iso2014Format = new SimpleDateFormat("yyyy-MM-dd");
        
        Date departureDate = iso2014Format.parse(configProperties.getProperty("departureDate"));
        Date returnDate = iso2014Format.parse(configProperties.getProperty("returnDate"));
        
        int numberOfAdults = Integer.parseInt(configProperties.getProperty("numberOfAdults"));
        
        TripParams tripParams = new TripParams(hotelName, departureAirportCode, destinationAirportCode, departureDate, returnDate, numberOfAdults);
        
        ScreenScraper screenScraper = new ScreenScraper(new StringSearchingScreenScraper());
        
        BigDecimal tripPrice = screenScraper.scrapeTripPrice(tripParams);
        
        String email = configProperties.getProperty("email");
        String password = configProperties.getProperty("password");
        
        Properties mailProperties = PropertiesLoader.loadProperties("config/mail.properties");
        Mailer mailer = new Mailer(email, password, mailProperties);
        mailer.sendMail("Trip Price", "Trip price for '" + hotelName + "': $" + tripPrice.toPlainString());
    }
}
