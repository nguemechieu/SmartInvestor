package com.smartinvestor.smartinvestor;

public class Bucket {


    double price, longCountPercent,shortCountPercent;

    @Override
    public String toString() {
        return "Bucket{" +
                "price=" + price +
                ", longCountPercent=" + longCountPercent +
                ", shortCountPercent=" + shortCountPercent +
                '}';
    }

    public Bucket(double price, double longCountPercent, double shortCountPercent) {
        this.price = price;
        this.longCountPercent = longCountPercent;
        this.shortCountPercent = shortCountPercent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLongCountPercent() {
        return longCountPercent;
    }

    public void setLongCountPercent(double longCountPercent) {
        this.longCountPercent = longCountPercent;
    }

    public double getShortCountPercent() {
        return shortCountPercent;
    }

    public void setShortCountPercent(double shortCountPercent) {
        this.shortCountPercent = shortCountPercent;
    }
}
