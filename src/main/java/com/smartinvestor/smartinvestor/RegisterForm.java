package com.smartinvestor.smartinvestor;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;
public class RegisterForm extends Stage {
    private static final String id =
            UUID.randomUUID().toString();
    public RegisterForm() throws SQLException {
        super();
      System.out.println("RegisterForm");
        DataSource db = new DataSource();
        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        gridPane.add(new Label("Username"), 0, 0);
        TextField varUsername=new TextField("Enter Username");
        gridPane.add(varUsername, 1, 0);
        gridPane.add(new Label("Password"), 0, 2);
        PasswordField varPassword=new PasswordField();
        gridPane.add(varPassword, 1, 2);
        gridPane.add(new Label("Confirm Password"), 0, 4);
        PasswordField varConfirmPassword=new PasswordField();
        gridPane.add(varConfirmPassword, 1, 4);
        gridPane.add(new Label("Email"), 0, 6);
        TextField varEmail=new TextField("Enter Email");
        gridPane.add(varEmail, 1, 6);
        gridPane.add(new Label("Phone"), 0, 8);
        TextField varPhone=new TextField("Enter Phone");
        gridPane.add(varPhone, 1, 8);
        gridPane.add(new Label("Address"), 0, 10);
        TextField varAddress=new TextField("Enter Address");
        gridPane.add(varAddress, 1, 10);
        gridPane.add(new Label("City"), 0, 12);
        TextField varCity=new TextField("Enter City");
        gridPane.add(varCity, 1, 12);
        gridPane.add(new Label("State"), 0, 14);
        TextField varState=new TextField("Enter State");
        gridPane.add(varState, 1, 14);
        gridPane.add(new Label("Country"), 0, 16);
        TextField varCountry=new TextField("Enter Country");
        gridPane.add(varCountry, 1, 16);
        gridPane.add(new Label("Zip Code"), 0, 18);
        TextField varZipCode=new TextField("Enter Zip Code");
        gridPane.add(varZipCode, 1, 18);
        gridPane.add(new Label("Birthdate"), 0, 22);
        DatePicker varBirthdate=new DatePicker();
        gridPane.add(varBirthdate, 1, 22);
        gridPane.add(new Label("Gender"), 0, 24);
        ChoiceBox<String> varGender=new ChoiceBox<>();
        varGender.getItems().add("Male");
        varGender.getItems().add("Female");
        varGender.getItems().add("Other")
                ;
        varGender.setValue("Male");

        gridPane.add(varGender, 2, 26);
        gridPane.add(new Label("ID"), 0, 26);
        Button varRegister=new Button("Submit");
        varRegister.setOnAction(e -> {
            close();
            System.out.println(varUsername.getText());
            System.out.println(varPassword.getText());
            System.out.println(varConfirmPassword.getText());
            System.out.println(varEmail.getText());
            System.out.println(varPhone.getText());
            System.out.println(varAddress.getText());
            System.out.println(varCity.getText());
            System.out.println(varState.getText());
            System.out.println(varCountry.getText());
            System.out.println(varZipCode.getText());
            System.out.println(varBirthdate.getValue());
            System.out.println(varGender.getValue());
            System.out.println(id);
            System.out.println(varUsername.getText());
            System.out.println(varPassword.getText());
            System.out.println(varConfirmPassword.getText());

            if (varUsername.getText().equals("") || varPassword.getText().equals("") || varConfirmPassword.getText().equals("") || varEmail.getText().equals("")
                    || varPhone.getText().equals("") || varAddress.getText().equals("") || varCity.getText().equals("") || varState.getText().equals("")|| varCountry.getText().equals("")|| varZipCode.getText().equals("")|| varBirthdate.getValue()==null || varGender.getValue().equals("")){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("All fields are required");
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            }else {
                try {
                    db.create(
                            varUsername.getText(),
                            varPassword.getText(),
                            varConfirmPassword.getText(),
                            varEmail.getText(),
                            varPhone.getText(),
                            varAddress.getText(),
                            varCity.getText(),
                            varState.getText(),
                            varCountry.getText(),
                            varZipCode.getText(),
                            varBirthdate.getValue(),
                            varGender.getValue(),
                            id
                    );
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }


        });
        gridPane.add(varRegister, 3, 28);
        Button varCancel=new Button("Cancel");
        varCancel.setOnAction(e -> close());
        gridPane.add(varCancel, 1, 28);
        TextField varID=new TextField(id);

        varID.setEditable(false);
        gridPane.add(varID, 0, 26);
        setTitle("Registration ");
        Scene scene = new Scene(gridPane, 700, 700);
        scene.getStylesheets().add(Objects.requireNonNull(RegisterForm.class.getResource("/app.css")).toExternalForm());
        setScene(scene);

        setAlwaysOnTop(true);
        setResizable(true);



        show();




    }
}
