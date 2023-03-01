package com.smartinvestor.smartinvestor;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.lang.System.out;

public class RequestHandler {

    private String api_key;

    public  RequestHandler(){



    }

    protected Object makeRequest(String url, String path) {
        if (path == null || path.length() == 0 || url == null || url.length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + path)).timeout(Duration.ofSeconds(5))
                .headers("Content-Type", "application/json",
                        "Authorization", "Bearer " + api_key)
              .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                out.println(response.body());
                return response.body();}
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(response.body());
                alert.showAndWait();
                return null;
            }


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }




}


    public void setApi_key(String s) {
        api_key = s;
    }

    public String getApi_key() {
        return api_key;
    }
}
