package com.smartinvestor.smartinvestor;

public interface Account {
    String getLastTransactionID();

    double getPendingTradeCount();

    double getFinancingCommission();

    double getDivident();

    void setAccountId(String asText);

    void setCurrency(String asText);

    void setBalance(double asDouble);

    void setAlias(String asText);

    void setTradeValueTime(String asText);

    void setTradeValueDate(String asText);

    void setTradeValue(double asDouble);

    void setTradeVolume(double asDouble);

    void setTradeCount(double asDouble);

    void setUnrealiazed(double asDouble);

    void setResettable(double asDouble);

    void setPendingTradeCount(double asDouble);

    void setPendingOrderCount(int asInt);

    void setHedging(int i);


    void setNav(double asDouble);

    void setLastTransactionID(String asText);

    void setAvailableBalance(double asDouble);

    void setCommission(double asDouble);

    void setDividend(double asDouble);

    void setLeverage(double asDouble);

    void setUnrealizedPL(double asDouble);

    void setMarginUsed(double asDouble);

    void setMarginRate(double asDouble);

    void setMarginAvailable(double asDouble);
}
