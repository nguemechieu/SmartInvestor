package com.smartinvestor.smartinvestor;

import javafx.scene.Node;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * An abstract base class for {@code Exchange} implementations.
 *
 * @author Michael Ennen
 */
public abstract class Exchange {
    protected final ExchangeWebSocketClient webSocketClient;

    protected Exchange(ExchangeWebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    public Exchange(String url) {
        this(new ExchangeWebSocketClient(URI.create(url),new Draft_6455()) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {

            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void streamLiveTrades(TradePair tradePair, LiveTradesConsumer liveTradesConsumer) {

            }

            @Override
            public void stopStreamLiveTrades(TradePair tradePair) {

            }

            @Override
            public boolean supportsStreamingTrades(TradePair tradePair) {
                return false;
            }
        });
    }

    /**
     * @return this exchange's {@code ExchangeWebSocketClient} instance, which is responsible for grabbing
     * live-streaming data (such as trades, orders, etc).
     */
    public ExchangeWebSocketClient getWebsocketClient() {
        return webSocketClient;
    }

    public abstract CompletableFuture<List<Trade>> fetchRecentTradesUntil(String tradePair, Instant stopAt);

    /**
     * Fetches the recent trades for the given trade pair from  {@code stopAt} till now (the current time).
     * <p>
     * This method only needs to be implemented to support live syncing.
     */
    public abstract CompletableFuture<List<Trade>> fetchRecentTradesUntil(TradePair tradePair, Instant stopAt);

    /**
     * Returns the {@code CandleDataSupplier} implementation that will be used to provide pages of candle data for the
     * given {@code secondsPerCandle} and {@code tradePair}.
     */
    public abstract CandleDataSupplier getCandleDataSupplier(int secondsPerCandle, TradePair tradePair);

    /**
     * Fetches completed candles (of smaller duration than the current {@code secondsPerCandle}) in the duration of
     * the current live-syncing candle.
     * <p>
     * TThis method only needs to be implemented to support live syncing.
     */
    public CompletableFuture<Optional<InProgressCandleData>> fetchCandleDataForInProgressCandle(
            TradePair tradePair, Instant currentCandleStartedAt, long secondsIntoCurrentCandle, int secondsPerCandle) {
        throw new UnsupportedOperationException("Exchange: " + this + " does not support fetching candle data" +
                " for in-progress candle");
    }

    public Node getOrderBook(TradePair tradepair) {
        return webSocketClient.getOrderBook(tradepair);
    }
}
