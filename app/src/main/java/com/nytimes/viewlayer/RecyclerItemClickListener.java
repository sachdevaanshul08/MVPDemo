package com.nytimes.viewlayer;


import com.nytimes.model.Article;

/**
 * Created by anshulsachdeva on 15/03/19.
 */

public interface RecyclerItemClickListener {
    void onItemClick(Article article);
}