package com.smartinvestor.smartinvestor;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

public class OrderBookVbox extends VBox {
    public OrderBookVbox(@NotNull Exchange exchange, TradePair tradepair) throws JsonProcessingException {
        super(10);
        this.getChildren().addAll(

                        exchange.getOrderBook(tradepair)

        );
                ;//,//<>(exchange.getOrderBook(tradepair))


    }
}
