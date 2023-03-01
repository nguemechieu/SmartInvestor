package com.smartinvestor.smartinvestor;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class SignUpButton extends Button {
    public SignUpButton() {
        super();
        this.setText("Sign Up");
        this.setTooltip(
                new Tooltip("Sign Up is a form that allows you to create a new user account.")
        );


        this.getStyleClass().add("sign-up-button");

    }
}
