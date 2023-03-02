package com.smartinvestor.smartinvestor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class App extends Application {
    @Override
    public void start(@NotNull Stage stage) throws SQLException, IOException, URISyntaxException, ExecutionException, InterruptedException, TimeoutException {

        new LoginForm(stage).show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}