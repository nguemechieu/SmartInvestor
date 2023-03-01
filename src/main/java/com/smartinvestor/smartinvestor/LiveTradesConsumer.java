package com.smartinvestor.smartinvestor;

import javafx.scene.Node;

import java.util.List;

/**
 * @author Michael Ennen
 */
public interface LiveTradesConsumer {
    void acceptTrades(List<Trade> trades);

    Node getOrderBook();
}
