package com.smartinvestor.smartinvestor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;


import static java.lang.System.out;

public class NewsManager {


    static String date;
    static double previous;
    static double forecast;
    static String title;
    static String impact;

    public NewsManager() {
        date = "";
        previous = 0.0;
        forecast = 0.0;
        title = "";
        impact = "";

    }
    @Contract(pure = true)
    public static @Nullable ArrayList<News> getNews()  {




        RequestHandler rest1 = new RequestHandler();

        rest1.setApi_key("");
        String url1 = "https://nfs.faireconomy.media";
        String path1 = "/ff_calendar_thisweek.json?version=1bed8a31256f1525dbb0b6daf6898823";

        Object response = rest1.makeRequest(url1, path1);
        rest1.setApi_key("o");
        if (response== null) {
            out.println("No response" );
            return null;
        }

        ArrayList<News> newsList =new ArrayList<>();

            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                JsonNode jsonNode = mapper.readTree( response.toString());
                for (JsonNode jsonNode1 :jsonNode) {
                date = jsonNode1.get("date").asText();
                title = jsonNode1.get("title").asText();
                impact = jsonNode1.get("impact").asText();
                forecast = jsonNode1.get("forecast").asDouble();
                previous = jsonNode1.get("previous").asDouble();

                    newsList.add(new News(date, title, impact, forecast, previous));
           }



        } catch (IOException e) {
                throw new RuntimeException(e);
            }


        return newsList;






 }


}
