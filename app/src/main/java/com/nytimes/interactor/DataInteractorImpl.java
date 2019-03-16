package com.nytimes.interactor;


import com.nytimes.AppConfig;
import com.nytimes.contract.AppContract.GetLatestDataInteractor;
import com.nytimes.model.ArticleList;
import com.nytimes.networklayer.ApiServiceGenerator;
import com.nytimes.networklayer.ResponseListener;
import com.nytimes.networklayer.WebCallback;
import com.nytimes.networklayer.webservice.GetPopularArticleApi;

import retrofit2.Call;


/**
 * Created by anshulsachdeva on 15/03/19.
 */

public class DataInteractorImpl implements GetLatestDataInteractor {


    @Override
    public void getArticleList(final OnResponseListener onResponseListener, String section, int period, String tag) {

        Call<ArticleList> call =
                ApiServiceGenerator.getInstance().getService(GetPopularArticleApi.class).getPopularArticles(section, period, AppConfig.API_KEY, tag);
        call.enqueue(new WebCallback<>(call, tag, new ResponseListener() {
            @Override
            public void onResponse(Object object, String tag) {
                if (onResponseListener != null) {
                    onResponseListener.onResponse(((ArticleList) object), tag);
                }
            }

            @Override
            public void onFailure(String message, String tag) {
                if (onResponseListener != null) {
                    onResponseListener.onFailure(message, tag);
                }
            }
        }));


    }

}
