package com.nytimes.presenter;

import com.nytimes.constant.Constants;
import com.nytimes.contract.AppContract.ArticleView;
import com.nytimes.contract.AppContract.GetLatestDataInteractor;
import com.nytimes.contract.AppContract.GetLatestDataInteractor.OnResponseListener;
import com.nytimes.contract.AppContract.Presenter;
import com.nytimes.model.ArticleList;
import com.nytimes.util.EspressoTestingIdlingResource;

/**
 * Created by anshulsachdeva on 15/03/19.
 */

public class PresenterImpl implements Presenter, OnResponseListener {

    private ArticleView mainView;
    private GetLatestDataInteractor getLatestDataIntractor;

    public PresenterImpl(ArticleView mainView, GetLatestDataInteractor getLatestDataInteractor) {
        this.mainView = mainView;
        this.getLatestDataIntractor = getLatestDataInteractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void requestDataFromServer() {
        if (mainView != null) {
            mainView.showProgress();
        }
        getLatestDataIntractor.getArticleList(this, Constants.ARTICLE_SECTION, Constants.PERIOD, Constants.REQUEST_TAG_GET_POPULAR_ARTICLE);

        // required for espresso UI testing
        EspressoTestingIdlingResource.increment();
    }


    @Override
    public void onResponse(ArticleList articleList, String tag) {
        if (mainView != null) {
            mainView.setContentToView(articleList);
            mainView.hideProgress();
        }
        // required for espresso UI testing
        EspressoTestingIdlingResource.decrement();
    }

    @Override
    public void onFailure(String message, String tag) {
        if (mainView != null) {
            mainView.onResponseFailure(message, tag);
            mainView.hideProgress();
        }
        // required for espresso UI testing
        EspressoTestingIdlingResource.decrement();
    }
}
