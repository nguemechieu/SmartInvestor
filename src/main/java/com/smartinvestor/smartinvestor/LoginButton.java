package com.smartinvestor.smartinvestor;


import javafx.scene.control.Button;

public class LoginButton extends Button{


    public LoginButton() {
        super();
        this.setText("Sign In");
        this.setId("loginButton");
        this.getStyleClass().add("loginButton");
        this.setOnAction(e -> {
            System.out.println("Login Button Clicked");
        });
        System.out.println("Login Button Created");


    }

}
