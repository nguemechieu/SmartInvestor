package com.smartinvestor.smartinvestor;

import javafx.scene.control.Hyperlink;

public class ForgotPassword extends Hyperlink {

    public ForgotPassword() {
        super();
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.getStyleClass().add("forgot-password-link");


        setText("Forgot Password?");
        getStyleClass().add("forgot-password-button");
        setUnderline(true);
        getChildren().addAll(forgotPasswordLink);

    }
}
