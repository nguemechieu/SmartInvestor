package com.smartinvestor.smartinvestor;

import java.net.URI;

public record OandaOrder(String side, double size, URI exchangeUrl, String tradePair) {
    public static void execute(String tradePair, String market, String side, int size) {
    }
}
