package com.smartinvestor.smartinvestor.oanda;

import java.io.Serial;

public class OandaException extends Throwable {
    @Serial
    private static final long serialVersionUID = 1L;

    public OandaException() {
        super();
    }

    public OandaException(String message) {
        super(message);
    }



}
