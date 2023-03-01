package com.smartinvestor.smartinvestor.oanda;

public interface CandleDataSupplier {
    CandleData getCandleData();
    void setCandleData(CandleData candleData);
    boolean isReady();
    void setReady(boolean ready);
    boolean isCancelled();
    void setCancelled(boolean cancelled);
}
