package com.smartinvestor.smartinvestor;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static java.lang.System.out;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static sun.jvm.hotspot.debugger.win32.coff.DebugVC50X86RegisterEnums.TAG;

public class Oanda extends Exchange {

   static String OANDA_API_KEY="0240ef2126049ea737dcab96be1de960-b6075c244149c2c201d2e1740ddfb511";
    static String OANDA_ACCOUNT_ID="001-001-2783446-006";
    String granularity="M30";

    Oanda(String url) {
        super(url);

         // This argument is for creating a WebSocket client for live trading data.
    }

    @Override
    public CompletableFuture<List<Trade>> fetchRecentTradesUntil(String tradePair, Instant stopAt) {
        return null;
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    @Override
    public CompletableFuture<List<Trade>> fetchRecentTradesUntil(TradePair tradePair, Instant stopAt) {
        return null;
    }

    public CandleDataSupplier getCandleDataSupplier(int secondsPerCandle, TradePair tradePair) {
        return new OandaCandleDataSupplier(secondsPerCandle, tradePair) ;
    }

    public void getPositions() {
    }

    public class OandaCandleDataSupplier extends CandleDataSupplier {
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        private static final int EARLIEST_DATA = 1422144000; // roughly the first trade

        OandaCandleDataSupplier(int secondsPerCandle, TradePair tradePair) {
            super(200, secondsPerCandle, tradePair, new SimpleIntegerProperty(-1));
        }

        @Override
        public Set<Integer> getSupportedGranularities() {
            // https://docs.pro.coinbase.com/#get-historic-rates


            return new TreeSet<>(Set.of(

                    60,
                    60*2,60*3,60*30,300,900,3600,3600*4,3600*6,3600*12,3600*24,3600*24*7,3600*24*7*4));
        }


        @Override
        public Future<List<CandleData>> get() {
            if (endTime.get() == -1) {
                endTime.set((int) (Instant.now().toEpochMilli() / 1000L));
            }

            String endDateString = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                    .format(LocalDateTime.ofEpochSecond(endTime.get(), 0, ZoneOffset.UTC));

            int startTime = Math.max(endTime.get() - (numCandles * secondsPerCandle), EARLIEST_DATA);
            String startDateString = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                    .format(LocalDateTime.ofEpochSecond(startTime, 0, ZoneOffset.UTC));
            //"https://api-fxtrade.oanda.com/v3/instruments/USD_CAD/candles?price=BA&from=2016-10-17T15%3A00%3A00.000000000Z&granularity=M1"

            String granularity
                    = "M30";

            //"https://api-fxtrade.oanda.com/v3/instruments/USD_CAD/candles?price=BA&from=2016-10-17T15%3A00%3A00.000000000Z&granularity=M1";
            String uriStr = "https://api-fxtrade.oanda.com/v3/instruments/"+tradePair.toString('_')+"/candles?price=BA&from="+startDateString+"&granularity="+granularity;





            if (startTime == EARLIEST_DATA) {
                // signal more data is false
                out.println(uriStr);
                return CompletableFuture.completedFuture(Collections.emptyList());
            }

            HttpRequest.Builder req = HttpRequest.newBuilder(
                    URI.create(uriStr)
            );
            req.header("Accept", "application/json");
            String apiKey=
                    OANDA_API_KEY;
            req.header("Authorization", "Bearer " + apiKey);
            return HttpClient.newHttpClient().sendAsync(
                           req.build(),
                            HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(response -> {
                        Log.info("   Oanda response: " ,response);
                        JsonNode res;
                        try {
                            res = OBJECT_MAPPER.readTree(response);
                            long time = Date.from(Instant.parse(res.get("time").asText())).getTime();
                            out.println(time);
                        if (!res.isEmpty()) {
                            // Remove the current in-progress candle
                            if (time + secondsPerCandle > endTime.get()) {
                                ((ArrayNode) res.get("time")).remove(0);
                            }
                            endTime.set(startTime);

                            List<CandleData> candleData = new ArrayList<>();
                            for (JsonNode candle : res.get("candles")) {

                                if (candle.has("ask")){
                                    for (JsonNode ask : candle.get("ask")) {


                                candleData.add(new CandleData(
                                        ask.get("o").asDouble(),  // open price
                                        ask.get("c").asDouble(),  // close price
                                        ask.get("h").asDouble(),  // high price
                                        ask.get("l").asDouble(),  // low price
                                        (int) time,     // open time
                                        candle.get("volume").asDouble() ));  // volume
                                        out.println(ask.get("o").asDouble() + " " + ask.get("c").
                                                asDouble() + " " + ask.get("h").
                                                asDouble() + " " + ask.get("l").
                                                asDouble() + " " + ask.get("volume").
                                                asDouble() + " " + ask.get("time").
                                                asDouble() + " " + ask.get("o").
                                                asDouble() + " " + ask.get("c").
                                                asDouble());
                                }}
                            }
                            candleData.sort(Comparator.comparingInt(CandleData::getOpenTime));
                            return candleData;
                        } else {
                            return Collections.emptyList();
                        }

                    } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        });
    }




    /**
     * Fetches the recent trades for the given trade pair from  {@code stopAt} till now (the current time).
     * <p>
     * This method only needs to be implemented to support live syncing.
     */

    public @NotNull CompletableFuture<List<Trade>> fetchRecentTradesUntil(TradePair tradePair, Instant stopAt) {
        Objects.requireNonNull(tradePair);
        Objects.requireNonNull(stopAt);

        if (stopAt.isAfter(Instant.now())) {
            return CompletableFuture.completedFuture(Collections.emptyList());
        }

        CompletableFuture<List<Trade>> futureResult = new CompletableFuture<>();

        // It is not easy to fetch trades concurrently because we need to get the "cb-after" header after each request.
        CompletableFuture.runAsync(() -> {
            IntegerProperty afterCursor = new SimpleIntegerProperty(0);
            List<Trade> tradesBeforeStopTime = new ArrayList<>();

            // For Public Endpoints, our rate limit is 3 requests per second, up to 6 requests per second in
            // burst.
            // We will know if we get rate limited if we get a 429 response code.
//
//            HTTP/1.1 200 OK
//            Access-Control-Allow-Headers: Authorization, Content-Type, Accept-Datetime-Format
//            Content-Encoding: gzip
//            Transfer-Encoding: chunked
//            Server: openresty/1.7.0.1
//            Connection: keep-alive
//            Link: <https://api-fxtrade.oanda.com/v3/accounts/<ACCOUNT>/trades?beforeID=6397&instrument=USD_CAD>; rel="next"
//            Date: Wed, 22 Jun 2016 18:41:48 GMT
//            Access-Control-Allow-Origin: *
//            Access-Control-Allow-Methods: PUT, PATCH, POST, GET, OPTIONS, DELETE
//            Content-Type: application/json

            for (int i = 0; !futureResult.isDone(); i++) {
                String accountID
                        = OANDA_ACCOUNT_ID;
                if (accountID == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR)
                            ;
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please set the OANDA_ACCOUNT_ID environment variable");
                    alert.showAndWait();
                    return
                            ;
                }
                String uriStr = "https://api-fxtrade.oanda.com/v3/accounts/"+accountID+"/trades?";
                uriStr += "instrument=" + tradePair.toString('_') ;

                if (i != 0) {
                    uriStr += "?after=" + afterCursor.get();
                }


                HttpRequest.Builder req = HttpRequest.newBuilder(URI.create(uriStr));
                req.header("Accept", "application/json");
                String apiKey=
                        OANDA_API_KEY;
                req.header("Authorization", "Bearer " + Objects.requireNonNullElse(apiKey, ""));
                req.header("Content-Type", "application/json");
                req.header("Connection", "keep-alive");
                req.header("Link", "<https://api-fxtrade.oanda.com/v3/accounts/"+accountID+"/trades?beforeID=6397&instrument="+tradePair.toString('_')+">; rel=\"next\"");
                req.header("Date", "Wed, 22 Jun 2016 1");
                req.header("Access-Control-Allow-Origin", "*");
                req.header("Access-Control-Allow-Methods", "PUT, PATCH, POST, GET, OPTIONS, DELETE");
                req.header("Content-Encoding", "gzip");
                req.header("Transfer-Encoding", "chunked");
                req.header("Server", "openresty/1.7.0.1");
                req.header("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept-Datetime-Format, Content-Encoding, Transfer-Encoding, Server, Connection, Link, Date, Access-Control-Allow-Origin, Access-Control-Allow-Methods, Access-Control-Allow-Headers");

                try {
                    HttpResponse<String> response = HttpClient.newHttpClient().send(
                           req.build(),
                            HttpResponse.BodyHandlers.ofString());

                    Log.info("response headers: " , String.valueOf(response.headers()));
                    if (response.headers().firstValue("CB-AFTER").isEmpty()) {
                        futureResult.completeExceptionally(new RuntimeException(
                                "oanda trades response did not contain header \"cb-after\": " + response));
                        return;
                    }

                    afterCursor.setValue(Integer.valueOf((response.headers().firstValue("CB-AFTER").get())));

                    JsonNode tradesResponse = OBJECT_MAPPER.readTree(response.body());

                    if (!tradesResponse.isArray()) {
                        futureResult.completeExceptionally(new RuntimeException(
                                "oanda trades response was not an array!"));
                    }
                    if (tradesResponse.isEmpty()) {
                        futureResult.completeExceptionally(new IllegalArgumentException("tradesResponse was empty"));
                    } else {
                        for (int j = 0; j < tradesResponse.size(); j++) {
                            JsonNode trade = tradesResponse.get(j);
                            Instant time = Instant.from(ISO_INSTANT.parse(trade.get("time").asText()));
                            if (time.compareTo(stopAt) <= 0) {
                                futureResult.complete(tradesBeforeStopTime);
                                break;
                            } else {
                                tradesBeforeStopTime.add(new Trade(tradePair,
                                        DefaultMoney.ofFiat(trade.get("price").asText(), tradePair.getCounterCurrency()),
                                        DefaultMoney.ofCrypto(trade.get("size").asText(), tradePair.getBaseCurrency()),
                                        Side.getSide(trade.get("side").asText()), trade.get("id").asLong(), time));
                            }
                        }
                    }
                } catch (IOException | InterruptedException ex) {
                    Log.error("ex: "+ ex);
                }

        }
    });
        return futureResult;
        }




        @Override
        public CompletableFuture<Optional<InProgressCandleData>> fetchCandleDataForInProgressCandle(
                TradePair tradePair, Instant currentCandleStartedAt, long secondsIntoCurrentCandle, int secondsPerCandle) {
            String startDateString = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.ofInstant(
                    currentCandleStartedAt, ZoneOffset.UTC));
            long idealGranularity = Math.max(10, secondsIntoCurrentCandle / 200);
            // Get the closest supported granularity to the ideal granularity.
            int actualGranularity = getCandleDataSupplier(secondsPerCandle, tradePair).getSupportedGranularities().stream()
                    .min(Comparator.comparingInt(i -> (int) Math.abs(i - idealGranularity)))
                    .orElseThrow(() -> new NoSuchElementException("Supported granularities was empty!"));
            // TODO: If actualGranularity = secondsPerCandle there are no sub-candles to fetch and we must get all the
            //  data for the current live syncing candle from the raw trades method.
            String uriStr = "https://api-fxtrade.oanda.com/v3/instruments/"+tradePair.toString('_')+"/candles?price=BA&from="+startDateString+"&granularity="+granularity;


            HttpRequest.Builder req=HttpRequest.newBuilder(
                    URI.create(uriStr
                    )
            );
            req.header("Accept", "application/json");
            req.header("Authorization", "Bearer " + OANDA_API_KEY);
            return HttpClient.newHttpClient().sendAsync(
                         req.build(),
                            HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(response -> {
                        Log.info(String.valueOf(TAG),"coinbase response: " + response);
                        JsonNode res;
                        try {
                            res = OBJECT_MAPPER.readTree(response);
                        } catch (JsonProcessingException ex) {
                            throw new RuntimeException(ex);
                        }

                        if (res.isEmpty()) {
                            return Optional.empty();
                        }

                        JsonNode currCandle;
                        Iterator<JsonNode> candleItr = res.iterator();
                        int currentTill = -1;
                        double openPrice = -1;
                        double highSoFar = -1;
                        double lowSoFar = Double.MAX_VALUE;
                        double volumeSoFar = 0;
                        double lastTradePrice = -1;
                        boolean foundFirst = false;
                        while (candleItr.hasNext()) {
                            currCandle = candleItr.next();
                            long time = Date.from(Instant.parse(currCandle.get("time").asText())).getTime();

                            if (time < currentCandleStartedAt.getEpochSecond() ||
                                    time>= currentCandleStartedAt.getEpochSecond() +
                                            secondsPerCandle) {
                                // skip this sub-candle if it is not in the parent candle's duration (this is just a
                                // sanity guard) TODO(mike): Consider making this a "break;" once we understand why
                                //  Coinbase is  not respecting start/end times
                                continue;
                            } else {
                                if (!foundFirst) {
                                    // FIXME: Why are we only using the first sub-candle here?
                                    currentTill = (int) time;
                                    lastTradePrice = currCandle.get("bid").get("o").asDouble();
                                    foundFirst = true;
                                }
                            }

                            openPrice = currCandle.get("bid").get("o").asDouble();;

                            if (openPrice > highSoFar) {
                                highSoFar = openPrice;
                            }

                            if (currCandle.get("bid").get("o").asDouble() < lowSoFar) {
                                lowSoFar = currCandle.get("bid").get("o").asDouble() ;
                            }

                            volumeSoFar += currCandle.get("volume").asDouble();
                        }

                        int openTime = (int) (currentCandleStartedAt.toEpochMilli() / 1000L);

                        return Optional.of(new InProgressCandleData(openTime, openPrice, highSoFar, lowSoFar,
                                currentTill, lastTradePrice, volumeSoFar));
                    });
        }


    }

    /**
     * This method only needs to be implemented to support live syncing
     * .
     */


    @Override
    public CompletableFuture<Optional<InProgressCandleData>> fetchCandleDataForInProgressCandle(
            TradePair tradePair, Instant currentCandleStartedAt, long secondsIntoCurrentCandle, int secondsPerCandle) {
        String startDateString = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.ofInstant(
                currentCandleStartedAt, ZoneOffset.UTC));
        long idealGranularity = Math.max(10, secondsIntoCurrentCandle / 200);
        // Get the closest supported granularity to the ideal granularity.
        int actualGranularity = getCandleDataSupplier(secondsPerCandle, tradePair).getSupportedGranularities().stream()
                .min(Comparator.comparingInt(i -> (int) Math.abs(i - idealGranularity)))
                .orElseThrow(() -> new NoSuchElementException("Supported granularities was empty!"));
        // TODO: If actualGranularity = secondsPerCandle there are no sub-candles to fetch and we must get all the
        //  data for the current live syncing candle from the raw trades method.
        String uriStr = "https://api-fxtrade.oanda.com/v3/instruments/"+tradePair.toString('_')+"/candles?price=BA&from="+startDateString+"&granularity="+granularity;


        HttpRequest.Builder req = HttpRequest.newBuilder(
                URI.create(uriStr

                )
        );
        req.header(
                "Authorization",
                "Bearer " + OANDA_API_KEY
        );
        
        
        
        
        return HttpClient.newHttpClient().sendAsync(
                        req.build(),
                        HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(response -> {
                    Log.info(String.valueOf(TAG),"coinbase response: " + response);
                    JsonNode res;
                    try {
                        res = OBJECT_MAPPER.readTree(response);
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (res.isEmpty()) {
                        return Optional.empty();
                    }

                    JsonNode currCandle;
                    Iterator<JsonNode> candleItr = res.iterator();
                    int currentTill = -1;
                    double openPrice = -1;
                    double highSoFar = -1;
                    double lowSoFar = Double.MAX_VALUE;
                    double volumeSoFar = 0;
                    double lastTradePrice = -1;
                    boolean foundFirst = false;
                    while (candleItr.hasNext()) {
                        currCandle = candleItr.next();
                        long time = Date.from(Instant.parse(currCandle.get("time").asText())).getTime();

                        if (time < currentCandleStartedAt.getEpochSecond() ||
                                time>= currentCandleStartedAt.getEpochSecond() +
                                        secondsPerCandle) {
                            // skip this sub-candle if it is not in the parent candle's duration (this is just a
                            // sanity guard) TODO(mike): Consider making this a "break;" once we understand why
                            //  Coinbase is  not respecting start/end times
                            continue;
                        } else {
                            if (!foundFirst) {
                                // FIXME: Why are we only using the first sub-candle here?
                                currentTill = (int) time;
                                lastTradePrice = currCandle.get("bid").get("o").asDouble();
                                foundFirst = true;
                            }
                        }

                        openPrice = currCandle.get("bid").get("o").asDouble();;

                        if (openPrice > highSoFar) {
                            highSoFar = openPrice;
                        }

                        if (currCandle.get("bid").get("o").asDouble() < lowSoFar) {
                            lowSoFar = currCandle.get("bid").get("o").asDouble() ;
                        }

                        volumeSoFar += currCandle.get("volume").asDouble();
                        out.println(currCandle);
                    }

                    int openTime = (int) (currentCandleStartedAt.toEpochMilli() / 1000L);

                    return Optional.of(new InProgressCandleData(openTime, openPrice, highSoFar, lowSoFar,
                            currentTill, lastTradePrice, volumeSoFar));
                });
    }
    
    
    
  public void getAccountInfo(String infoEndpoint) throws IOException, InterruptedException {
      String uriStr = "https://api-fxtrade.oanda.com/v3/accounts/" + OANDA_ACCOUNT_ID + "/" + infoEndpoint;
      HttpRequest.Builder req = HttpRequest.newBuilder();
      req.header(
              "Authorization",
              "Bearer " + OANDA_API_KEY
      );
      req.header(
              "Accept",
              "application/json"
      );
      req.header(
              "Content-Type",
              "application/json"
      );
      req.header(
              "User-Agent",

              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36"
      );

      req.header("Cache-Control", "no-cache");
      req.header("Accept-Encoding",
              "gzip, deflate, br");

      out.println(uriStr);
      req.GET().uri(URI.create(uriStr));

      HttpClient client = HttpClient.newHttpClient();
      HttpResponse.BodyHandler<?> resp
              = HttpResponse.BodyHandlers.ofString();
      HttpResponse<?> response = client.send(
              req.build(),
              resp);
      //.thenApply(HttpResponse::body)


      if (response.statusCode() == 200) {
          Log.info(String.valueOf(TAG), "Oanda account info  response: " + response.toString());



      } else {
          out.println(
                  "Oanda account info " + response.statusCode() + "response: " + response.toString()
          );



      }
      ;


  }

    }

