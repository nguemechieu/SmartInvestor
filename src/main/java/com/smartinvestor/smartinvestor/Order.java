package com.smartinvestor.smartinvestor;

public class Order {
    private double shortCountPercent;
    private double longCountPercent;
    private double price;
    private double amount;

    @Override
    public String toString() {
        return "Order{" +
                "shortCountPercent=" + shortCountPercent +
                ", longCountPercent=" + longCountPercent +
                ", price=" + price +
                ", amount=" + amount +
                ", side='" + side + '\'' +
                ", id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    private String side;
    private String id;
    private long timestamp;
    private String status;
    private String type;
    private double quantity;

    public void setShortCountPercent(double shortCountPercent) {
        this.shortCountPercent = shortCountPercent;
    }

    public double getShortCountPercent() {
        return shortCountPercent;
    }

    public void setLongCountPercent(double longCountPercent) {
        this.longCountPercent = longCountPercent;
    }

    public double getLongCountPercent() {
        return longCountPercent;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setAmount(double amount) {
        this.amount= amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTimestamp(long toEpochMilli) {
        this.timestamp = toEpochMilli;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setQuantity(double quantity) {
        this.quantity= quantity;
    }

    public double getQuantity() {
        return quantity;
    }
}
