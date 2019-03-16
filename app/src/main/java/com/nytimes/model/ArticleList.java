package com.nytimes.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anshulsachdeva on 15/03/19.
 */

public class ArticleList {

    @SerializedName("results")
    private List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
