package com.smartinvestor.smartinvestor;

import java.net.URI;

public record BinanceUsOrder(String side, double size, URI exchangeUrl, String tradePair) {
    public static void execute(String tradePair, String market, String side, double size) {
        System.out.printf("BinanceUsOrder: %s %s %s %s%n", tradePair, market, side, size);

    }
}
