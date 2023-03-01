package com.smartinvestor.smartinvestor;

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

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import static com.smartinvestor.smartinvestor.NewsManager.getNews;


public class MainScreen extends Stage {



    public MainScreen() throws Exception {

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
            System.out.println("New");
        });
        fileMenu.getItems().get(1).setOnAction(event -> {
            System.out.println("Open");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File file = fileChooser.showOpenDialog(this);
            if (file != null) {
                System.out.println(file.getAbsolutePath());
            } else {
                System.out.println("No file selected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No file selected");
                alert.showAndWait();
            }

        });
        fileMenu.getItems().get(2).setOnAction(event -> {
            System.out.println("Save");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File file = fileChooser.showSaveDialog(this);
            if (file != null) {
                System.out.println(file.getAbsolutePath());
            } else {
                System.out.println("No file selected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No file selected");
                alert.showAndWait();
            }
        });
        fileMenu.getItems().get(3).setOnAction(event -> {
            System.out.println("Save As");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File file = fileChooser.showSaveDialog(this);
            if (file != null) {
                System.out.println(file.getAbsolutePath());
            } else {
                System.out.println("No file selected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No file selected");
                alert.showAndWait();
            }
        });
        fileMenu.getItems().get(4).setOnAction(event -> System.out.println("Print"));
        fileMenu.getItems().get(5).setOnAction(event -> System.out.println("Exit"));
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
            System.out.println("Undo");

        });
        editMenu.getItems().get(1).setOnAction(event -> {
            System.out.println("Redo");

        });
        editMenu.getItems().get(2).setOnAction(event -> {
            System.out.println("Cut");


        });
        editMenu.getItems().get(3).setOnAction(event -> {
            System.out.println("Copy");


        });
        editMenu.getItems().get(4).setOnAction(event -> {
            System.out.println("Paste");


        });
        editMenu.getItems().get(5).setOnAction(event -> {
            System.out.println("Select All");


        });
        editMenu.getItems().get(6).setOnAction(event -> {
            System.out.println("Delete");


        });

        editMenu.getItems().get(7).setOnAction(event -> {
            System.out.println("Find");


        });
        editMenu.getItems().get(8).setOnAction(event -> {
            System.out.println("Find Next");


        });
        editMenu.getItems().get(9).setOnAction(event -> {
            System.out.println("Find Previous");


        });
        editMenu.getItems().get(10).setOnAction(event -> {
            System.out.println("Select All");


        });
        editMenu.getItems().get(11).setOnAction(event -> {
            System.out.println("Find");


        });
        editMenu.getItems().get(12).setOnAction(event -> {
            System.out.println("Find Next");


        });
        editMenu.getItems().get(13).setOnAction(event -> {
            System.out.println("Find Previous");


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


        TradePair EUR_USD =   TradePair.of(Currency.of("EUR"), Currency.ofFiat("USD")),

                BTC_USD=    TradePair.of(Currency.of("BTC"), Currency.ofFiat("USD"))

                ,XLM_USD = TradePair.of(Currency.ofCrypto("XLM"), Currency.of("BTC"));
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
        scene.getClass().getResource("/app.css");
        getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/SmartInvestor.png"))));
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


    TreeTableView<Currency> treeTableView =new TreeTableView<>();
//
    treeTableView.setPadding(new Insets(20, 20, 20, 20));
    treeTableView.setPrefSize(1500, 450);
    treeTableView.setTranslateY(50);
    treeTableView.setTranslateX(20);

        TreeItem <Currency> root = new TreeItem<>();








return treeTableView;

    }


    /**
     * Example of how to use the CandleFX API to create a candle stick chart for the BTC/USD tradepair on Coinbase.
     */

}