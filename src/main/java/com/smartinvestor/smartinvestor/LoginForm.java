package com.smartinvestor.smartinvestor;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginForm extends Stage {
    GridPane grid = new GridPane();
    public LoginForm() {

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        grid.add(new Label("Username :"), 0, 0);
        TextField username=new TextField();
        username.setPromptText("Enter username");
        grid.add(username, 1, 0);
        grid.add(new Label("Password :"), 0, 1);
        PasswordField password = new PasswordField();
        password.setPromptText("Enter password");
        grid.add(password, 1, 1);
        CheckBox rememberMeField = new CheckBox("Remember me");
        grid.add(rememberMeField, 1, 2);
        LoginButton loginButton = new LoginButton();
        loginButton.setText("Login ");
        loginButton.setOnAction(

                event -> {
                    try {
                        verifyLogin(
                                username.getText(),
                                password.getText()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        grid.add(loginButton, 2, 3);
        SignUpButton signUpButton= new SignUpButton();
        signUpButton.setText("Register");
        signUpButton.setOnAction(
                event -> {
                    try {
                        new RegisterForm();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }


        );
        grid.add(signUpButton, 1
                , 3);

        ForgotPassword forgotPassword=new ForgotPassword();
        forgotPassword.setOnAction(
                event -> {   System.out.println("Forgot Password ");


                    GridPane gridPane=new GridPane();
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

                    gridPane.setAlignment(javafx.geometry.Pos.CENTER);
                    gridPane.add(new Label("Email :"), 0, 0);
                    TextField email=new TextField();
                    email.setPromptText("Enter your email");
                    gridPane.add(email, 1, 0);
                    Button submitButton=new Button("Submit");



                    submitButton.setOnAction(
                            event1 -> {
                                try {
                                    verifyLogin(
                                            username.getText(),password.getText()
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });

                    Button cancelButton=new Button("Cancel");
                    cancelButton.setOnAction(event1 -> {close();new LoginForm();});
                    gridPane.add(submitButton, 1, 7);
                    gridPane.add(cancelButton, 1, 6);
                    close();
                    AnchorPane anchorPane=new AnchorPane();
                    gridPane.setTranslateY(200);
                    gridPane.setTranslateX(500);
                    anchorPane.getChildren().add(gridPane);

                    Scene scene = new Scene(anchorPane,1530,780);
                    scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/app.css")).toExternalForm());
                    setScene(scene);
                    setTitle("Forgot Password");
                    show();

                }
        );

        grid.add(forgotPassword, 1, 6);
        grid.setTranslateX(0);
        grid.setTranslateY(0);
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        grid.setPrefSize(200, 100);
        Scene scene = new Scene(grid,1530,780);
        setTitle("Smart Investor ");
        getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/SmartInvestor.png"))));
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/app.css")).toExternalForm());

        setResizable(true);
        setIconified(true);
        setScene(scene);

        show();




    }

    private void verifyLogin( String username, String password) throws Exception {
        if (username==null || password==null) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter username and password");

            alert.showAndWait();

        }
        else {
            System.out.println(username);
            System.out.println(password);
            if (username.equals("admin") && password.equals("1234")) {

                System.out.println("Login Successful");
                System.out.println(username);
                System.out.println(password);
                Alert alert = new Alert(
                        Alert.AlertType.INFORMATION,
                        "Login Successful"
                );
                alert.showAndWait();
                this.close();

                 new MainScreen().show();
            }
            else {

                DataSource db = new DataSource();
               if (db.findOne("user", "username", username)){
                   System.out.println("Login Failed");
                   System.out.println(username);
                   System.out.println(password);
                   Alert alert = new Alert(Alert.AlertType.ERROR, "Login Failed");
                   alert.showAndWait();
               }else {
                   if (db.findOne("user", "password", password)){
                       System.out.println("Login Failed");
                       System.out.println(username);
                       System.out.println(password);
                       Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect password.\nLogin Failed");
                       alert.showAndWait();
                   }else {
                       System.out.println("Login Successful");
                       System.out.println(username);
                       System.out.println(password);
                       Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Successful");
                       alert.showAndWait();
                       this.close();

                       new MainScreen().show();
                   }
               }






                System.out.println("Login Failed");
                System.out.println(username);
                System.out.println(password);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Login Failed");
                alert.showAndWait();
            }
        }



    }
}
