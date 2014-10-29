package com.arahlf.tripmonitor;

import java.math.BigDecimal;

public class StringSearchingScreenScraper implements ScreenScrapingStrategy {
    @Override
    public BigDecimal scrapeTripPrice(String hotelName, String html) {
        
        int startOfHotelInfo = html.indexOf(hotelName);
        int insideLastFontTagBefore = html.indexOf("Vacationer(s)", startOfHotelInfo);
        
        String startOfContainerNodeHtml = "<font size=\"1pt;\" >";
        String endOfContainerNodeHtml = "</font>";
        
        int startOfContainerNode = html.indexOf(startOfContainerNodeHtml, insideLastFontTagBefore);
        int endOfContainerNode = html.indexOf(endOfContainerNodeHtml, startOfContainerNode);
        
        String tripPrice = html.substring(startOfContainerNode + startOfContainerNodeHtml.length(), endOfContainerNode);
        tripPrice = tripPrice.replace("*", "");
        tripPrice = tripPrice.replace("$", "");
        
        return new BigDecimal(tripPrice);
    }
}
