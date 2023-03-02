package com.smartinvestor.smartinvestor;


public interface MoneyFormatter<T extends Money> {
    String format(T money);
}
