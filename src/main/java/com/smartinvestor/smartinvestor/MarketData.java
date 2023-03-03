package com.smartinvestor.smartinvestor;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class MarketData extends RecursiveTreeObject<MarketData> {

    private  String id;
    private String lastUpdated;
    private String symbol;
    private String name;
    private String image;
    private String currentPrice;
    private String marketCap;
    private String marketCapRank;
    private String fullyDilutedValuation;
    private String totalVolume;
    private String high24h;
    private String low24h;
    private String priceChange24h;
    private String priceChangePercentage24h;
    private String marketCapChange24h;
    private String marketCapChangePercentage24h;
    private String circulatingSupply;
    private String totalSupply;
    private String maxSupply;
    private String atlDate;
    private String roi;
    private String atlChangePercentage;
    private String athChangePercentage;

    public void setAtlDate(String atlDate) {
        this.atlDate = atlDate;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public void setAtlChangePercentage(String atlChangePercentage) {
        this.atlChangePercentage = atlChangePercentage;
    }

    public void setAthChangePercentage(String athChangePercentage) {
        this.athChangePercentage = athChangePercentage;
    }

    public void setAth(String ath) {
        this.ath = ath;
    }

    private String ath;

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public void setMarketCapRank(String marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public void setFullyDilutedValuation(String fullyDilutedValuation) {
        this.fullyDilutedValuation = fullyDilutedValuation;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }

    public void setHigh24h(String high24h) {
        this.high24h = high24h;
    }

    public void setLow24h(String low24h) {
        this.low24h = low24h;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public String getMarketCapRank() {
        return marketCapRank;
    }

    public String getFullyDilutedValuation() {
        return fullyDilutedValuation;
    }

    public String getTotalVolume() {
        return totalVolume;
    }

    public String getHigh24h() {
        return high24h;
    }

    public String getLow24h() {
        return low24h;
    }

    public String getPriceChange24h() {
        return priceChange24h;
    }

    public String getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public String getMarketCapChange24h() {
        return marketCapChange24h;
    }

    public String getMarketCapChangePercentage24h() {
        return marketCapChangePercentage24h;
    }

    public String getCirculatingSupply() {
        return circulatingSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public void setPriceChange24h(String priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    public void setPriceChangePercentage24h(String priceChangePercentage24h) {
        this.priceChangePercentage24h = priceChangePercentage24h;
    }

    public void setMarketCapChange24h(String marketCapChange24h) {
        this.marketCapChange24h = marketCapChange24h;
    }

    public void setMarketCapChangePercentage24h(String marketCapChangePercentage24h) {
        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
    }

    public void setCirculatingSupply(String circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public MarketData(String lastUpdated, String symbol, String name, String image, String currentPrice, String marketCap, String marketCapRank, String fullyDilutedValuation, String totalVolume, String high24h, String low24h, String priceChange24h, String priceChangePercentage24h, String marketCapChange24h, String marketCapChangePercentage24h, String circulatingSupply, String totalSupply, String maxSupply, String atlDate, String roi, String atlChangePercentage, String athChangePercentage, String ath, String last_updated) {
        this.lastUpdated = lastUpdated;
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.marketCapRank = marketCapRank;
        this.fullyDilutedValuation = fullyDilutedValuation;
        this.totalVolume = totalVolume;
        this.high24h = high24h;
        this.low24h = low24h;
        this.priceChange24h = priceChange24h;
        this.priceChangePercentage24h = priceChangePercentage24h;
        this.marketCapChange24h = marketCapChange24h;
        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.atlDate = atlDate;
        this.roi = roi;
        this.atlChangePercentage = atlChangePercentage;
        this.athChangePercentage = athChangePercentage;
        this.ath = ath;
    }

    public MarketData(String id, String symbol, String name, String image, String currentPrice, String lastUpdated,String marketCap, String marketCapRank, String fullyDilutedValuation, String totalVolume, String high24h, String low24h, String priceChange24h, String priceChangePercentage24h, String marketCapChange24h, String marketCapChangePercentage24h, String circulatingSupply, String totalSupply, String maxSupply) {
        this.lastUpdated = lastUpdated;
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.marketCapRank = marketCapRank;
        this.fullyDilutedValuation = fullyDilutedValuation;
        this.totalVolume = totalVolume;
        this.high24h = high24h;
        this.low24h = low24h;
        this.priceChange24h = priceChange24h;
        this.priceChangePercentage24h = priceChangePercentage24h;
        this.marketCapChange24h = marketCapChange24h;
        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;

    }

    @Override
    public String toString() {
        return "MarketData{" +
                "lastUpdated='" + lastUpdated + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                ", marketCap='" + marketCap + '\'' +
                ", marketCapRank='" + marketCapRank + '\'' +
                ", fullyDilutedValuation='" + fullyDilutedValuation + '\'' +
                ", totalVolume='" + totalVolume + '\'' +
                ", high24h='" + high24h + '\'' +
                ", low24h='" + low24h + '\'' +
                ", priceChange24h='" + priceChange24h + '\'' +
                ", priceChangePercentage24h='" + priceChangePercentage24h + '\'' +
                ", marketCapChange24h='" + marketCapChange24h + '\'' +
                ", marketCapChangePercentage24h='" + marketCapChangePercentage24h + '\'' +
                ", circulatingSupply='" + circulatingSupply + '\'' +
                ", totalSupply='" + totalSupply + '\'' +
                '}';
    }


    public String getAtlDate() {
        return atlDate;
    }

    public String getRoi() {
        return roi;
    }

    public String getAtlChangePercentage() {
        return atlChangePercentage;
    }

    public String getAtl() {
        return atlDate;
    }



    public String getAth() {
        return ath;
    }

    public String getId() {
        return id;
    }
}
