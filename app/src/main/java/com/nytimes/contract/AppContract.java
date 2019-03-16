package com.nytimes.contract;

import com.nytimes.model.ArticleList;

/**
 * Created by anshulsachdeva on 15/03/19.
 *
 */

public interface AppContract {

    /**
     * When user interact with view(s)
     */
    interface Presenter {

        void onDestroy();

        //To request the data from the server
        void requestDataFromServer();

    }

    /**
     *
     * **/
    interface ArticleView {

        //To show the progress bar on the screen
        void showProgress();

        //To hide the progress bar on the screen
        void hideProgress();

        //To set the content on the recycler view when loaded from server
        void setContentToView(ArticleList articleList);

        //To update the view just in case if response failed
        void onResponseFailure(String message, String tag);

    }

    /**
     * To fetch the data from NY times web APIs.
     **/
    interface GetLatestDataInteractor {

        interface OnResponseListener {
            void onResponse(ArticleList articleList, String tag);

            void onFailure(String message, String tag);
        }

        void getArticleList(OnResponseListener onResponseListener, String section, int period, String tag);
    }
}
