package com.nytimes.networklayer.webservice;

import com.nytimes.model.ArticleList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anshulsachdeva on 15/03/19.
 */

public interface GetPopularArticleApi {

    /**
     *
     * */
    @GET("mostviewed/{section}/{period}.json")
    Call<ArticleList> getPopularArticles(@Path("section") String section, @Path("period") int period,
                                        @Query("api-key") String apiKey, @Header("mTag") String tag);


}
