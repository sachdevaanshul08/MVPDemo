package com.nytimes.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anshulsachdeva on 15/03/19.
 */

public class Article {

    @SerializedName("url")
    private String url;
    @SerializedName("byline")
    private String byLine;
    @SerializedName("title")
    private String title;
    @SerializedName("published_date")
    private String publishedDate;
    @SerializedName("abstract")
    private String abstractLine;

    public String getUrl() {
        return url;
    }

    public String getByLine() {
        return byLine;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getAbstractLine() {
        return abstractLine;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setAbstractLine(String abstractLine) {
        this.abstractLine = abstractLine;
    }
}
