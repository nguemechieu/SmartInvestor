package com.smartinvestor.smartinvestor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class OrderBook  {
    private final BlockingQueue<Trade> liveTradesQueue;
    double bucketWidth;
    long unixTime;
    static double price;
    private static Double longCoinPercent;
    private static Double shortCoinPercent;
    String time;
    String instrument;


    static List <Bucket>bucketList = new ArrayList<>();

    public OrderBook(BlockingQueue<Trade> liveTradesQueue) {
        super(
        )
        ;
        bucketList.add(new Bucket(0.0,0.0,0.0));
        this.liveTradesQueue = liveTradesQueue;

    }

    @Override
    public String toString() {
        return "OrderBook" +
                "bucketWidth=" + bucketWidth +
                ", unixTime=" + unixTime +
                ", price=" + price +
                ", time='" + time + '\'' +
                ", instrument='" + instrument + '\'' +
                ", bucketList=" + bucketList;
    }

    public OrderBook(BlockingQueue<Trade> liveTradesQueue, String instrument, String time, long unixTime, double price, double bucketWidth, List<Bucket> bucketList) {
        super();
        this.liveTradesQueue = liveTradesQueue;


        this.instrument = instrument;
        this.time = time;
        this.unixTime = unixTime;
        OrderBook.price = price;
        this.bucketWidth = bucketWidth;

        OrderBook.bucketList = bucketList;

    }

    public static void addBucketList(Double price,Double longCoinPercent,Double shortCoinPercent ) {
        OrderBook.price = price;
        OrderBook.longCoinPercent = longCoinPercent;
        OrderBook.shortCoinPercent = shortCoinPercent;


        bucketList.add(new Bucket(
                        price,
                        longCoinPercent,shortCoinPercent));




    }


}
