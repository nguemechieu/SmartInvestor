module com.smartinvestor.smartinvestor {
    uses com.smartinvestor.smartinvestor.CurrencyDataProvider;
    uses com.smartinvestor.smartinvestor.ServiceProvider;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.jetbrains.annotations;
    requires java.xml;
    requires java.logging;
    requires java.sql;
    requires java.desktop;
    requires java.sql.rowset;


    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires com.google.protobuf;
    requires org.json;
    requires com.jfoenix;

    requires Java.WebSocket;

    requires javafx.web;
    requires javafx.graphics;
    requires javafx.media;

    requires com.fasterxml.jackson.dataformat.csv;


    requires mysql.connector.j;
    requires slf4j.api;
    requires jdk.jsobject;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jdk.hotspot.agent;

    opens com.smartinvestor.smartinvestor to javafx.fxml;
    exports com.smartinvestor.smartinvestor;
}