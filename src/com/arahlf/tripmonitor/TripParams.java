package com.arahlf.tripmonitor;

import java.util.Date;

public class TripParams {
    
    public TripParams(String hotelName, String departureAirportCode, String destinationAirportCode, Date departureDate, Date returnDate, int numberOfAdults) {
        _hotelName = hotelName;
        _departureAirportCode = departureAirportCode;
        _destinationAirportCode = destinationAirportCode;
        _departureDate = departureDate;
        _returnDate = returnDate;
        _numberOfAdults = numberOfAdults;
    }
    
    public String getHotelName() {
        return _hotelName;
    }
    
    public String getDepartureAirportCode() {
        return _departureAirportCode;
    }
    
    public String getDestinationAirportCode() {
        return _destinationAirportCode;
    }
    
    public Date getDepartureDate() {
        return _departureDate;
    }
    
    public Date getReturnDate() {
        return _returnDate;
    }
    
    public int getNumberOfAdults() {
        return _numberOfAdults;
    }
    
    private final String _hotelName;
    private final String _departureAirportCode;
    private final String _destinationAirportCode;
    private final Date _departureDate;
    private final Date _returnDate;
    private final int _numberOfAdults;
}
