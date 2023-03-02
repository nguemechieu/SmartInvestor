package com.smartinvestor.smartinvestor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.FXPermission;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collector;

import static com.smartinvestor.smartinvestor.NewsManager.getNews;
import static java.lang.System.out;


public class MainScreen extends Stage {



    public MainScreen() throws Exception, TelegramApiException {

        super();
        close();
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> Log.error("[" + thread + "]: "+exception));
        MenuBar menuBar = new MenuBar();
        Menu[] menus = new Menu[]{
                new Menu("File"),
                new Menu("Edit"),
                new Menu("Charts"),
                new Menu("Indicators"),
                new Menu("Messages"),
                new Menu("About")
        };
        for (Menu menu : menus) {
            menuBar.getMenus().add(menu);
        }

        Menu fileMenu = menuBar.getMenus().get(0);
        fileMenu.getItems().addAll(
                new MenuItem("New"),
                new MenuItem("Open"),
                new MenuItem("Save"),
                new MenuItem("Save As"),
                new SeparatorMenuItem(),
                new MenuItem("Print to PDF"),
                new MenuItem("Exit"));
        fileMenu.getItems().get(0).setOnAction(event -> {
            out.println("New");
        });
        fileMenu.getItems().get(1).setOnAction(event -> {
            out.println("Open");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File file = fileChooser.showOpenDialog(this);
            if (file != null) {
                out.println(file.getAbsolutePath());
            } else {
                out.println("No file selected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No file selected");
                alert.showAndWait();
            }

        });
        fileMenu.getItems().get(2).setOnAction(event -> {
            out.println("Save");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File file = fileChooser.showSaveDialog(this);
            if (file != null) {
                out.println(file.getAbsolutePath());
            } else {
                out.println("No file selected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No file selected");
                alert.showAndWait();
            }
        });
        fileMenu.getItems().get(3).setOnAction(event -> {
            out.println("Save As");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File file = fileChooser.showSaveDialog(this);
            if (file != null) {
                out.println(file.getAbsolutePath());
            } else {
                out.println("No file selected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No file selected");
                alert.showAndWait();
            }
        });
        fileMenu.getItems().get(4).setOnAction(event -> out.println("Print"));
        fileMenu.getItems().get(5).setOnAction(event -> out.println("Exit"));
        fileMenu.getItems().get(6).setOnAction(event -> System.exit(0));


        Menu editMenu = menuBar.getMenus().get(1);
        editMenu.getItems().addAll(
                new MenuItem("Undo"),
                new MenuItem("Redo"),
                new SeparatorMenuItem(),
                new MenuItem("Cut"),
                new MenuItem("Copy"),
                new MenuItem("Paste"),
                new SeparatorMenuItem(),
                new MenuItem("Select All"),
                new SeparatorMenuItem(),
                new MenuItem("Delete"),
                new SeparatorMenuItem(),
                new MenuItem("Find"),
                new SeparatorMenuItem(),
                new MenuItem("Find Next"),
                new SeparatorMenuItem(),
                new MenuItem("Find Previous"),
                new SeparatorMenuItem(),
                new MenuItem("Select All"),
                new SeparatorMenuItem());


        editMenu.getItems().get(0).setOnAction(event -> {
            out.println("Undo");

        });
        editMenu.getItems().get(1).setOnAction(event -> {
            out.println("Redo");

        });
        editMenu.getItems().get(2).setOnAction(event -> {
            out.println("Cut");


        });
        editMenu.getItems().get(3).setOnAction(event -> {
            out.println("Copy");


        });
        editMenu.getItems().get(4).setOnAction(event -> {
            out.println("Paste");


        });
        editMenu.getItems().get(5).setOnAction(event -> {
            out.println("Select All");


        });
        editMenu.getItems().get(6).setOnAction(event -> {
            out.println("Delete");


        });

        editMenu.getItems().get(7).setOnAction(event -> {
            out.println("Find");


        });
        editMenu.getItems().get(8).setOnAction(event -> {
            out.println("Find Next");


        });
        editMenu.getItems().get(9).setOnAction(event -> {
            out.println("Find Previous");


        });
        editMenu.getItems().get(10).setOnAction(event -> {
            out.println("Select All");


        });
        editMenu.getItems().get(11).setOnAction(event -> {
            out.println("Find");


        });
        editMenu.getItems().get(12).setOnAction(event -> {
            out.println("Find Next");


        });
        editMenu.getItems().get(13).setOnAction(event -> {
            out.println("Find Previous");


        });


        Menu chartsMenu = menuBar.getMenus().get(2);
        chartsMenu.getItems().addAll(
                new MenuItem("Candlestick Chart"),
                new MenuItem("Renko Chart"),
                new MenuItem("Fibonacci Chart"),
                new MenuItem("Line Chart"),
                new MenuItem("Area Chart"),
                new MenuItem("Bar Chart"),
                new MenuItem("Scatter Chart"),
                new MenuItem("Pie Chart"),
                new MenuItem("Radar Chart"),
                new SeparatorMenuItem());


        Menu indicatorsMenu = menuBar.getMenus().get(3);
        indicatorsMenu.getItems().addAll(
                new MenuItem("RSI"),
                new MenuItem("Stochastic"),
                new MenuItem("MACD"),
                new MenuItem("True Range"),
                new SeparatorMenuItem(),
                new MenuItem("MA"),
                new MenuItem("Stochastic"),
                new MenuItem("CCI"),
                new MenuItem("Trend"),
                new SeparatorMenuItem());


        Menu messagesMenu = menuBar.getMenus().get(4);

        Menu aboutMenu = menuBar.getMenus().get(5);

        AnchorPane stackPane = new AnchorPane(
                menuBar
        );
        stackPane.setPadding(new Insets(20, 20, 20, 20));
        stackPane.setPrefSize(1530, 780);
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.BOTTOM);
        DraggableTab investmentTab = new DraggableTab("Trade");
        investmentTab.setId("trade");
        DraggableTab portfolioTab = new DraggableTab("Portfolio");
        DraggableTab strategyTesterTab = new DraggableTab("Strategy Tester");
        DraggableTab mailTab = new DraggableTab("Mail ");
        DraggableTab signalTab = new DraggableTab("Trade Signal");
        DraggableTab accountDetailsTab = new DraggableTab("Accounts Details");
        GridPane oandaGridPane= new GridPane();
        oandaGridPane.setAlignment(Pos.CENTER);
        oandaGridPane.setHgap(10);
        oandaGridPane.setVgap(10);
        oandaGridPane.setPadding(new Insets(10, 10, 10, 10));


        oandaGridPane.add(new Label("OANDA ID"), 0,0);
        TextField varOandaID = new TextField();
        varOandaID.setEditable(false);
        oandaGridPane.add(varOandaID, 1,0);
        oandaGridPane.add(new Label("Ticker"), 0,1);
        TextField varTicker = new TextField();
        varTicker.setEditable(false);
        oandaGridPane.add(varTicker, 1,1);
        oandaGridPane.add(new Label("Balance"), 0,2);
        TextField varBalance = new TextField();
        varBalance.setEditable(false);

        oandaGridPane.add(varBalance, 1,2);
        oandaGridPane.add(new Label("Currency"), 0,3);
        TextField varCurrency = new TextField();
        varCurrency.setEditable(false);
        oandaGridPane.add(varCurrency, 1,3);
        oandaGridPane.add(new Label("Margin"), 0,4);
        TextField varMargin = new TextField();
        varMargin.setEditable(false);

        oandaGridPane.add(varMargin, 1,4);
        oandaGridPane.add(new Label("Margin Available"), 0,5);
        TextField varMarginAvailable = new TextField();

        oandaGridPane.add(varMarginAvailable, 1,5);
        oandaGridPane.add(new Label("Profit /Losses"), 0,6);
        TextField varProfitLosses = new TextField();
        varProfitLosses.setEditable(false);
        oandaGridPane.add(varProfitLosses, 1,6);
        oandaGridPane.add(new Label("Positions"), 0,7);
        TextField varPositions = new TextField();

        oandaGridPane.add(varPositions, 1,7);







        GridPane coinbaseGridPane= new GridPane();
        coinbaseGridPane.setAlignment(Pos.CENTER);
        coinbaseGridPane.setHgap(10);
        coinbaseGridPane.setVgap(10);
        coinbaseGridPane.setPadding(new Insets(10, 10, 10, 10));

        coinbaseGridPane.add(new Label("Coinbase ID"), 0,0);
        TextField varCoinbaseID = new TextField();
        varCoinbaseID.setEditable(false);
        coinbaseGridPane.add(varCoinbaseID, 1,0);
        coinbaseGridPane.add(new Label("Ticker"), 0,1);
        TextField varTicker0 = new TextField();
        varTicker.setEditable(false);
        coinbaseGridPane.add(varTicker0, 1,1);
        coinbaseGridPane.add(new Label("Balance"), 0,2);
        TextField varBalance0 = new TextField();
        varBalance0.setEditable(false);
        coinbaseGridPane.add(varBalance0, 1,2);
        coinbaseGridPane.add(new Label("Currency"), 0,3);
        TextField varCurrency0 = new TextField();
        varCurrency0.setEditable(false);
        coinbaseGridPane.add(varCurrency0, 1,3);
        coinbaseGridPane.add(new Label("Margin"), 0,4);
        TextField varMargin0 = new TextField();
        varMargin0.setEditable(false);
        coinbaseGridPane.add(varMargin0, 1,4);
        coinbaseGridPane.add(new Label("Margin Available"), 0,5);
        TextField varMarginAvailable0 = new TextField();
        varMarginAvailable0.setEditable(false);
        coinbaseGridPane.add(varMarginAvailable0, 1,5);
        coinbaseGridPane.add(new Label("Profit /Losses"), 0,6);
        TextField varProfitLosses0 = new TextField();
        varProfitLosses0.setEditable(false);
        coinbaseGridPane.add(varProfitLosses0, 1,6);
        coinbaseGridPane.add(new Label("Positions"), 0,7);
        TextField varPositions0 = new TextField();
        varPositions0.setEditable(false);
        coinbaseGridPane.add(varPositions0, 1,7);
        coinbaseGridPane.setTranslateX(200);

        GridPane binanceGridPane= new GridPane();
        binanceGridPane.setAlignment(Pos.CENTER);
        binanceGridPane.setHgap(10);
        binanceGridPane.setVgap(10);
        binanceGridPane.setPadding(new Insets(10, 10, 10, 10));

        binanceGridPane.add(new Label("Binance ID"), 0,0);
        TextField varBinanceID = new TextField();
        varBinanceID.setEditable(false);
        binanceGridPane.add(varBinanceID, 1,0);
        binanceGridPane.add(new Label("Ticker"), 0,1);
        TextField varTicker1 = new TextField();
        varTicker1.setEditable(false);
        binanceGridPane.add(varTicker1, 1,1);
        binanceGridPane.add(new Label("Balance"), 0,2);
        TextField varBalance1 = new TextField();
        varBalance1.setEditable(false);
        binanceGridPane.add(varBalance1, 1,2);
        binanceGridPane.add(new Label("Currency"), 0,3);
        TextField varCurrency1 = new TextField();
        varCurrency1.setEditable(false);
        binanceGridPane.add(varCurrency1, 1,3);
        binanceGridPane.add(new Label("Margin"), 0,4);
        TextField varMargin1 = new TextField();
        varMargin1.setEditable(false);
        binanceGridPane.add(varMargin1, 1,4);
        binanceGridPane.add(new Label("Margin Available"), 0,5);
        TextField varMarginAvailable1 = new TextField();
        varMarginAvailable1.setEditable(false);
        binanceGridPane.add(varMarginAvailable1, 1,5);
        binanceGridPane.add(new Label("Profit /Losses"), 0,6);
        TextField varProfitLosses1 = new TextField();
        varProfitLosses1.setEditable(false);
        binanceGridPane.add(varProfitLosses1, 1,6);
        binanceGridPane.add(new Label("Positions"), 0,7);
        TextField varPositions1 = new TextField();
        varPositions1.setEditable(false);
        binanceGridPane.add(varPositions1, 1,7);
        binanceGridPane.setTranslateX(300);


        accountDetailsTab.setContent(
                new FlowPane(
                        new VBox(
                                new Label("Oanda Account Details")
                                ,oandaGridPane
                        ),
                        new VBox(
                                new Label("Coinbase  Account Details"),
                                coinbaseGridPane
                        ),
                        new VBox(
                                new Label("Binance Us Account Details"),

                                binanceGridPane
                        )
                )
        );
        DraggableTab stocksTab = new DraggableTab("Stocks");

        TreeTableView<News> newsTreeTableView = new TreeTableView<>();
        newsTreeTableView.setEditable(false);
        newsTreeTableView.setPadding(new Insets(20, 20, 20, 20));
        newsTreeTableView.setPrefSize(
                1530,
                600
        );


        assert getNews() != null;
        ObservableList<News> news = FXCollections.observableArrayList();
        news.addAll(getNews());
        TreeItem<News> root = new TreeItem<>();



        root.setExpanded(true);
        newsTreeTableView.setRoot(root);
        TreeTableColumn<News, String> titleColumn = new TreeTableColumn<>();
        titleColumn.setText("Title");
        titleColumn.setPrefWidth(200);
        titleColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(
                param.getValue().getValue().getTitle())
        );

        TreeTableColumn<News, String> dateColumn = new TreeTableColumn<>("Date");
        dateColumn.setPrefWidth(200);
        dateColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().date));

        TreeTableColumn<News, String> impactColumn = new TreeTableColumn<>("Impact");
        impactColumn.setPrefWidth(200);
        impactColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().impact));

        TreeTableColumn<News, String> forecastColumn = new TreeTableColumn<>("Forecast");

        forecastColumn.setPrefWidth(200);
        forecastColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper((String.valueOf(param.getValue().getValue().forecast))));

        TreeTableColumn<News, String> previousColumn = new TreeTableColumn<>("Previous");
        previousColumn.setPrefWidth(200);
        previousColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper((String.valueOf(param.getValue().getValue().previous))));

        root.setValue(
                news.get(0)
        );
        root.setExpanded(true);
        root.getChildren().setAll(news);
        //noinspection unchecked
        newsTreeTableView.getColumns().addAll(
                titleColumn,
                dateColumn,
                impactColumn,
                forecastColumn,
                previousColumn
        );

        DraggableTab news_tab = new DraggableTab("News");
        news_tab.setClosable(true);
        news_tab.setContent(new VBox(new Label("Trading News"), newsTreeTableView));

        DraggableTab browser = new DraggableTab("Browser");

        browser.setContent(new Browser());

        DraggableTab home = new DraggableTab("Home");

        home.setContent(new VBox(new Label("Home")));
        home.setClosable(true);

        boolean liveSyncing = true;


        TradePair EUR_USD =   TradePair.of("EUR", "USD"),

                BTC_USD=    TradePair.of("BTC", "USD")

                ,XLM_USD = TradePair.of("XLM", "USD");
      CandleStickChartContainer coinbaseStickChartContainer = new CandleStickChartContainer(
                new coinbase(
                        "wss://advanced-trade-ws.coinbase.com"
                ),
                 BTC_USD, liveSyncing);
        Oanda oanda=       new Oanda("https://api.oanda.com/v3/instrument/BTCUSD");
//
//
//        GET	/v3/accounts
//        Get a list of all Accounts authorized for the provided token.
//
//
//                GET	/v3/accounts/{accountID}
//        Get the full details for a single Account that a client has access to. Full pending Order, open Trade and open Position representations are provided.
//
//
//                GET	/v3/accounts/{accountID}/summary
//        Get a summary for a single Account that a client has access to.
//
//
//                GET	/v3/accounts/{accountID}/instruments
//        Get the list of tradeable instruments for the given Account. The list of tradeable instruments is dependent on the regulatory division that the Account is located in, thus should be the same for all Accounts owned by a single user.
//
//
//                PATCH	/v3/accounts/{accountID}/configuration
//        Set the client-configurable portions of an Account.


        //GET	/v3/accounts/{accountID}/changes
        //Endpoint used to poll an Account for its current state and changes since a specified TransactionID.

        oanda.getAccountInfo("summary");
        oanda.getAccountInfo("instruments");

        oanda.getAccountInfo("positions");
        oanda.getAccountInfo("transactions");
        oanda.getAccountInfo("orders");
        oanda.getAccountInfo("trades");
        oanda.getPositions();

        CandleStickChartContainer oandaCandleStickChartContainer = new CandleStickChartContainer(
         oanda,
                 EUR_USD, liveSyncing);
        CandleStickChartContainer binanceUsCandleStickChartContainer = new CandleStickChartContainer(
                new BinanceUs(
                        "wss://stream.binance.us:9443"
                ),
                 XLM_USD, liveSyncing);


        DraggableTab coinbaseTab = new DraggableTab("Coinbase");
        DraggableTab oandaTab = new DraggableTab("Oanda");

        DraggableTab binanceUsTab = new DraggableTab("Binance Us");







       // oandaCandleStickChartContainer,
         //       binanceUsCandleStickChartContainer

        coinbaseTab.setContent(coinbaseStickChartContainer);
        oandaTab.setContent(oandaCandleStickChartContainer);
        binanceUsTab.setContent(binanceUsCandleStickChartContainer);
        home.setContent(new VBox(new Label("Home"),getCurrenciesData()));
        tabPane.getTabs().addAll(
                home,oandaTab,coinbaseTab,binanceUsTab ,
                portfolioTab, stocksTab,news_tab, browser,
                signalTab, accountDetailsTab, mailTab, strategyTesterTab);



        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);
        tabPane.setPrefSize(1500, 500);
        tabPane.setTranslateY(50);
        tabPane.setTranslateX(10);
        stackPane.getChildren().addAll(tabPane);
        stackPane.setPrefSize(
                1500,
                780);
        Scene scene = new Scene(stackPane, 1530, 780);
        setTitle("Smart Investor      " + DateFormat.getDateInstance().format(new Date()));
        setResizable(true);
        centerOnScreen();
        getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/SmartInvestor.png"))));

        scene.getStylesheets().add(Objects.requireNonNull(Objects.requireNonNull(this.getClass().getResource("/app.css")).toExternalForm()));

        setScene(scene);
    }
    TreeTableView<Currency> getCurrenciesData() throws Exception {


        //
//        "id": "bitcoin",
//                "symbol": "btc",
//                "name": "Bitcoin",
//                "image": "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
//                "current_price": 23147,
//                "market_cap": 447608917862,
//                "market_cap_rank": 1,
//                "fully_diluted_valuation": 486923970804,
//                "total_volume": 24865914317,
//                "high_24h": 23552,
//                "low_24h": 23154,
//                "price_change_24h": -262.0034461846444,
//                "price_change_percentage_24h": -1.11923,
//                "market_cap_change_24h": -2790454422.5164795,
//                "market_cap_change_percentage_24h": -0.61955,
//                "circulating_supply": 19304425,
//                "total_supply": 21000000,
//                "max_supply": 21000000,
//                "ath": 69045,
//                "ath_change_percentage": -66.28606,
//                "ath_date": "2021-11-10T14:24:11.849Z",
//                "atl": 67.81,
//                "atl_change_percentage": 34228.35233,
//                "atl_date": "2013-07-06T00:00:00.000Z",
//                "roi": null,
//                "last_updated": "2023-02-28T21:38:58.066Z"
        ArrayList<Currency> currencies = new ArrayList<>();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        requestBuilder.GET().uri(URI.create("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false"));
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
        requestBuilder.setHeader("X-CMC_PRO_API_KEY", "9q3vfhm7l33rus21toc8fndupq76itje");
        requestBuilder.setHeader("Accept-Language", "en-US,en;q=0.5");

        HttpRequest request = requestBuilder.build();
        request.uri();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        out.println(response.body());
        out.println("Status "+response.statusCode());
        if (response.statusCode() == 200) {
            out.println(response.body());
            out.println("Status "+response.statusCode());
            out.println(response.body());

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

            JsonNode root0 = mapper.readTree(response.body());

            for (JsonNode node : root0) {
                out.println(node.get("id").asText());
                out.println(node.get("symbol").asText());
                out.println(node.get("name").asText());
                out.println(node.get("image").asText());
                out.println(node.get("current_price").asText());
                out.println(node.get("market_cap").asText());
                out.println(node.get("market_cap_rank").asText());
                out.println(node.get("fully_diluted_valuation").asText());
                out.println(node.get("total_volume").asText());
                out.println(node.get("high_24h").asText());
                out.println(node.get("low_24h").asText());
                out.println(node.get("price_change_24h").asText());
                out.println(node.get("price_change_percentage_24h").asText());
                out.println(node.get("market_cap_change_24h").asText());
                out.println(node.get("market_cap_change_percentage_24h").asText());
                out.println(node.get("circulating_supply").asText());
                out.println(node.get("total_supply").asText());
                out.println(node.get("max_supply").asText());
                out.println(node.get("ath").asText());
                out.println(node.get("ath_change_percentage").asText());

                out.println(node.get("atl").asText());
                out.println(node.get("atl_change_percentage").asText());
                out.println(node.get("atl_date").asText());
                out.println(node.get("roi").asText());
                out.println(node.get("last_updated").asText());
                Currency currency=new Currency(node.get("id").asText(), node.get("symbol").asText(), node.get("name").asText(), node.get("image").asText(), node.get("current_price").asText(), node.get("market_cap").asText(), node.get("market_cap_rank").asText(), node.get("fully_diluted_valuation").asText(), node.get("total_volume").asText(),

                        node.get("high_24h").asText(), node.get("low_24h").asText(), node.get("price_change_24h").asText(), node.get("price_change_percentage_24h").asText(), node.get("market_cap_change_24h").asText(), node.get("market_cap_change_percentage_24h").asText(), node.get("circulating_supply").asText(),
                        node.get("total_supply").asText(), node.get("max_supply").asText(), node.get("ath").asText(), node.get("ath_change_percentage").asText(), node.get("atl").asText(), node.get("atl_change_percentage").asText(), node.get("atl_date").asText(), node.get("roi").asText(), node.get("last_updated").asText());
               out.println(currency.toString());
                currencies.add(currency);


            }
            out.println("Currencies "+currencies);






    TreeTableView<Currency> treeTableView =new TreeTableView<>();
//
    treeTableView.setPadding(new Insets(20, 20, 20, 20));
    treeTableView.setPrefSize(1500, 450);
    treeTableView.setTranslateY(50);
    treeTableView.setTranslateX(20);

        TreeItem <Currency> root = new TreeItem<>();
        ObservableList<Currency> currencies0 = FXCollections.observableArrayList();
        currencies0.addAll(currencies);
        root.setValue(currencies0.get(0));

            Currency.registerCurrencies(currencies);


                root.setExpanded(true);
        treeTableView.setRoot(root);















          TreeTableColumn<Currency,String>  symbolColumn= new TreeTableColumn<>("Symbol");
          symbolColumn.setPrefWidth(50);
          symbolColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getSymbol()));


          TreeTableColumn<Currency,String>  nameColumn= new TreeTableColumn<>("Name");
          nameColumn.setPrefWidth(50);
          nameColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getName()));

          TreeTableColumn<Currency,String>  imageColumn= new TreeTableColumn<>("Image");
          imageColumn.setPrefWidth(50);
          imageColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getImage()));

          TreeTableColumn<Currency,String>  currentPriceColumn= new TreeTableColumn<>("Current Price");
          currentPriceColumn.setPrefWidth(50);
          currentPriceColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getCurrentPrice()));

          TreeTableColumn<Currency,String>  marketCapColumn= new TreeTableColumn<>("Market Cap");
          marketCapColumn.setPrefWidth(50);
          marketCapColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getMarketCap()));

          TreeTableColumn<Currency,String>  marketCapRankColumn= new TreeTableColumn<>("Market Cap Rank");
          marketCapRankColumn.setPrefWidth(50);
          marketCapRankColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getMarketCapRank()));

          TreeTableColumn<Currency,String>  fullyDilutedValuationColumn= new TreeTableColumn<>("Fully Diluted Valuation");
          fullyDilutedValuationColumn.setPrefWidth(50);
          fullyDilutedValuationColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getFullyDilutedValuation()));

          TreeTableColumn<Currency,String>  totalVolumeColumn= new TreeTableColumn<>("Total Volume");
          totalVolumeColumn.setPrefWidth(50);
          totalVolumeColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getTotalVolume()));

          TreeTableColumn<Currency,String>  high24hColumn= new TreeTableColumn<>("High 24h");
          high24hColumn.setPrefWidth(50);
          high24hColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getHigh24h()));

          TreeTableColumn<Currency,String>  low24hColumn= new TreeTableColumn<>("Low 24h");
          low24hColumn.setPrefWidth(50);
          low24hColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getLow24h()));

          TreeTableColumn<Currency,String>  priceChange24hColumn= new TreeTableColumn<>("Price Change 24h");
          priceChange24hColumn.setPrefWidth(50);

          priceChange24hColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getPriceChange24h()));

          TreeTableColumn<Currency,String>  priceChangePercentage24hColumn= new TreeTableColumn<>("Price Change Percentage 24h");
          priceChangePercentage24hColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getPriceChangePercentage24h()));
          priceChangePercentage24hColumn.setPrefWidth(50);

          TreeTableColumn<Currency,String>  marketCapChange24hColumn= new TreeTableColumn<>("Market Cap Change 24h");

          marketCapChange24hColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getMarketCapChange24h()));
          marketCapChange24hColumn.setPrefWidth(50);

          TreeTableColumn<Currency,String>  marketCapChangePercentage24hColumn= new TreeTableColumn<>("Market Cap Change Percentage 24h");
          marketCapChangePercentage24hColumn.setPrefWidth(50);

          marketCapChangePercentage24hColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getMarketCapChangePercentage24h()));


          TreeTableColumn<Currency,String>  circulatingSupplyColumn= new TreeTableColumn<>("Circulating Supply");
          circulatingSupplyColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getCirculatingSupply()));

          TreeTableColumn<Currency,String>  totalSupplyColumn= new TreeTableColumn<>("Total Supply");
          totalSupplyColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getTotalSupply()));

          TreeTableColumn<Currency,String>  maxSupplyColumn= new TreeTableColumn<>("Max Supply");
          maxSupplyColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getMaxSupply()));

          TreeTableColumn<Currency,String>  athColumn= new TreeTableColumn<>("ATH");
          athColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getAth()));

          TreeTableColumn<Currency,String>  athChangePercentageColumn= new TreeTableColumn<>("ATH Change Percentage");
          athChangePercentageColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getAthChangePercentage()));

          TreeTableColumn<Currency,String>  atlColumn= new TreeTableColumn<>("ATL");
          atlColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getAtl()));

          TreeTableColumn<Currency,String>  atlChangePercentageColumn= new TreeTableColumn<>("ATL Change Percentage");
          atlChangePercentageColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getAtlChangePercentage()));

          TreeTableColumn<Currency,String>  atlDateColumn= new TreeTableColumn<>("ATL Date");
          atlDateColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getAtlDate()));

          TreeTableColumn<Currency,String>  roiColumn= new TreeTableColumn<>("ROI");
          roiColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getRoi()));

          TreeTableColumn<Currency,String>  lastUpdatedColumn= new TreeTableColumn<>("Last Updated");
          lastUpdatedColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getLastUpdated()));









        treeTableView.getColumns().addAll(
                symbolColumn,
                nameColumn,
                imageColumn,
                currentPriceColumn,
                marketCapColumn,
                marketCapRankColumn,
                fullyDilutedValuationColumn,
                totalVolumeColumn,
                high24hColumn,
                low24hColumn,
                priceChange24hColumn,
                priceChangePercentage24hColumn,
                marketCapChange24hColumn,
                marketCapChangePercentage24hColumn,
                circulatingSupplyColumn,
                totalSupplyColumn,
                maxSupplyColumn,
                athColumn,
                athChangePercentageColumn,
                atlColumn,
                atlChangePercentageColumn,
                atlDateColumn,
                roiColumn,
                lastUpdatedColumn

        );





treeTableView.setPrefSize(1000  , 450);


return treeTableView;

        }else {
            out.println(response.body());
            out.println("Status "+response.statusCode());
            out.println(response.body());
            Log.error(response.statusCode(), response.body());
            return null;
        }

    }



    /**
     * Example of how to use the CandleFX API to create a candle stick chart for the BTC/USD tradepair on Coinbase.
     */

}