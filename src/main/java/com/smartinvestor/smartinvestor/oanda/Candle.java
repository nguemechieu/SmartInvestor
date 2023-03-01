package com.smartinvestor.smartinvestor.oanda;

public class Candle implements CandleData{

    public double open;
    public double high;
    public double low;
    public double close;
    public double volume;
    public double time;
    public String symbol;
    public String exchange;
    public String type;
    public String id;
    public String name;
    public String currency;
    public String baseAsset;
    public String quoteAsset;
    public String settle;
    public String status;
    public String side;
    public String timeInForce;
    public String postOnly;


    double bid,ask;

    @Override
    public double getOpen() {
        return open;
    }

    @Override
    public double getHigh() {
        return high;
    }

    @Override
    public double getLow() {
        return low;
    }

    @Override
    public double getClose() {
        return close;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public long getTime() {
        return (long) time;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getExchange() {
        return exchange;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public double getQuote() {
        return Double.parseDouble(quoteAsset);
    }

    @Override
    public double getQuoteVolume() {
        return 0;
    }

    @Override
    public double getAsk() {
        return  ask;
    }

    @Override
    public double getBid() {
        return bid;
    }
}
