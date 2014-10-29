package com.arahlf.tripmonitor;

import java.math.BigDecimal;

public interface ScreenScrapingStrategy {
    
    public BigDecimal scrapeTripPrice(String hotelName, String html);
}
