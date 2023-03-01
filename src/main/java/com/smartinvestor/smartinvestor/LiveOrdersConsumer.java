package com.smartinvestor.smartinvestor;

public interface LiveOrdersConsumer {
    void consume(LiveOrder liveOrder);
    void close();

}
