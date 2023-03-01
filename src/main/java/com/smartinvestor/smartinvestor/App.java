package com.smartinvestor.smartinvestor;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class App extends Application {
    @Override
    public void start(@NotNull Stage stage) {

        new LoginForm();

    }

    public static void main(String[] args) {
        launch(args);

    }
}