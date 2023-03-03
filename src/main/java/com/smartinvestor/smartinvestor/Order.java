package com.smartinvestor.smartinvestor;

public class Order {
    private double shortCountPercent;
    private double longCountPercent;
    private double price;
    private double amount;
    private String symbol;
    private double resettablePL;
    private double unrealizedPL;
    private double financingCommission;
    private double realizedPL;

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

    public void setOrderId(String orderId) {
        this.id = orderId;
    }

    public void setSymbol(String instrument) {
        this.symbol = instrument;
    }

    public void setResetablePL(double resetabblePL) {
this.resettablePL = resetabblePL;
    }

    public void setCommission(double commission) {this.commission = commission;}
    public double getCommission() {return commission;}
    double commission;


    public void setFinancingCommission(double financing) {
        this.financingCommission = financing;
    }

    public void setDivident(double divident) {
    }

    public void setLeverage(double leverage) {
        this.leverage = leverage;
    }
    public double getLeverage() {
        return leverage;
    }
double leverage;
    public void setUnrealizedPL(double unrealizedPL) {
        this.unrealizedPL = unrealizedPL;
    }

    public double getFinancingCommission() {
        return financingCommission;
    }

    public double getResettablePL() {
        return resettablePL;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getUnrealizedPL() {
        return unrealizedPL;
    }

    public void setRealizedPL(double realizedPL) {
        this.realizedPL = realizedPL;
    }

    public double getRealizedPL() {
        return realizedPL;
    }
}
