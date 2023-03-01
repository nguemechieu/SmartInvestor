package com.smartinvestor.smartinvestor.oanda;

public interface CandleData {
    double getOpen();
    double getHigh();
    double getLow();
    double getClose();
    double getVolume();
    long getTime();
    String getSymbol();
    String getExchange();
    String getCurrency();
    double getQuote();
    double getQuoteVolume();
    double getAsk();
    double getBid();

}
