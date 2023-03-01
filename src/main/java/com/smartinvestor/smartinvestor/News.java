package com.smartinvestor.smartinvestor;


import javafx.scene.control.TreeItem;

import java.sql.Date;
import java.time.Instant;

public class News extends TreeItem<News> {
     double previous;
    double forecast;
    String impact;
    String title;
    String date;
    private String link;
    private String thumbnail;

    public News  (String date, String title, String impact, double forecast, double previous) {

          this.date = date;
          this.title = title;
          this.impact = impact;

          this.forecast = forecast;
          this.previous = previous;
          this.setExpanded(true);
          this.setValue(this);
          this.link = "https://www.smartinvestor.com/news/"+title;
          this.thumbnail = "https://www.smartinvestor.com/images/news/"+title+".jpg";

      }
    public News() {

    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "  " +
                "date='" + date + '\''+
                 ", title='" + title + '\'' +
                ", impact='" + impact + '\'' +
                ", forecast=" + forecast+
                        "previous=" + previous
                ;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getImpact() {
        return impact;
    }
    public void setImpact(String impact) {
        this.impact = impact;
    }
    public double getForecast() {
        return forecast;
    }
    public void setForecast(double forecast) {
        this.forecast = forecast;
    }
    public double getPrevious() {
        return previous;
    }
    public void setPrevious(double previous) {
        this.previous = previous;
    }


    public void setLink(String url) {
        this.link = url;
    }

    public String getLink() {
        return link;
    }

    public void setThumbnail(String urlToImage) {
        this.thumbnail = urlToImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getMinutes() {
        return
                (int) ((Date.valueOf(date).getTime() -
                        Instant.now().toEpochMilli()) / 1000 / 60);



    }
}
