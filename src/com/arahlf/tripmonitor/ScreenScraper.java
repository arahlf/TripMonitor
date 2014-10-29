package com.arahlf.tripmonitor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ScreenScraper {
    
    public ScreenScraper(ScreenScrapingStrategy strategy) {
        _strategy = strategy;
    }
    
    public BigDecimal scrapeTripPrice(TripParams tripParams) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("command", "handleAppleVacationPackageSearch");
        params.put("searchType", "applePackage");
        params.put("selectedGateway", tripParams.getDepartureAirportCode());
        params.put("selectedDestination", tripParams.getDestinationAirportCode());
        params.put("numberOfAdults", Integer.toString(tripParams.getNumberOfAdults()));
        
        Calendar departureCalendar = Calendar.getInstance();
        departureCalendar.setTime(tripParams.getDepartureDate());
        
        params.put("departureYear", Integer.toString(departureCalendar.get(Calendar.YEAR)));
        params.put("departureMonth", _zeroPadToTwoDigits(departureCalendar.get(Calendar.MONTH) + 1));
        params.put("departureDay", _zeroPadToTwoDigits(departureCalendar.get(Calendar.DATE)));
        
        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.setTime(tripParams.getReturnDate());
        
        params.put("returnYear", Integer.toString(returnCalendar.get(Calendar.YEAR)));
        params.put("returnMonth", _zeroPadToTwoDigits(returnCalendar.get(Calendar.MONTH) + 1));
        params.put("returnDay", _zeroPadToTwoDigits(returnCalendar.get(Calendar.DATE)));
        
        StringBuilder builder = new StringBuilder();
        
        for (Entry<String, String> param : params.entrySet()) {
            builder.append(param.getKey() + "=" + param.getValue() + "&");
        }
        
        builder.deleteCharAt(builder.length() - 1);
        
        URL url = new URL("http://www.book.applevacations.com/search.do");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(builder.toString().length()));
        connection.setDoOutput(true);
        
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        
        writer.write(builder.toString());
        writer.flush();
        writer.close();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            response.append(line + File.separator);
        }
        
        reader.close();
        
        return _strategy.scrapeTripPrice(tripParams.getHotelName(), response.toString());
    }
    
    private String _zeroPadToTwoDigits(int number) {
        String stringified = Integer.toString(number);
        
        if (stringified.length() == 1) {
            stringified = "0" + stringified;
        }
        
        return stringified;
    }
    
    private final ScreenScrapingStrategy _strategy;
}
