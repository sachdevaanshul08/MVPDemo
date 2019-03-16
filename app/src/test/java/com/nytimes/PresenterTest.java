package com.nytimes;

/**
 * Created by anshulsachdeva on 16/03/19.
 */

import com.nytimes.contract.AppContract.ArticleView;
import com.nytimes.contract.AppContract.GetLatestDataInteractor;
import com.nytimes.contract.AppContract.GetLatestDataInteractor.OnResponseListener;
import com.nytimes.contract.AppContract.Presenter;
import com.nytimes.model.Article;
import com.nytimes.model.ArticleList;
import com.nytimes.presenter.PresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PresenterTest {

    @Mock
    GetLatestDataInteractor interactor;

    @Mock
    ArticleView view;

    @Captor
    private ArgumentCaptor<OnResponseListener> argumentCaptor;

    private Presenter presenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new PresenterImpl(view, interactor);

    }

    @Test
    public void testDisplayResult() {
        presenter.requestDataFromServer();
        verify(view, times(1)).showProgress();
        verify(interactor, times(1)).getArticleList(argumentCaptor.capture(), eq("all-sections"), eq(7), eq("REQUEST_TAG_GET_POPULAR_ARTICLE"));
        argumentCaptor.getValue().onResponse(getArticles(), eq("REQUEST_TAG_GET_POPULAR_ARTICLE"));
        verify(view, times(1)).hideProgress();
        //Argument Capturing
        ArgumentCaptor<ArticleList> entityArgumentCaptor = ArgumentCaptor.forClass(ArticleList.class);
        verify(view).setContentToView(entityArgumentCaptor.capture());
        assertTrue(entityArgumentCaptor.getValue().getArticleList().size() == 1);

    }


    @Test
    public void testDisplayError() {
        presenter.requestDataFromServer();
        verify(view, times(1)).showProgress();
        verify(interactor, times(1)).getArticleList(argumentCaptor.capture(), eq("all-sections"), eq(7), eq("REQUEST_TAG_GET_POPULAR_ARTICLE"));
        argumentCaptor.getValue().onFailure("Something went wrong", "REQUEST_TAG_GET_POPULAR_ARTICLE");
        verify(view, times(1)).hideProgress();
        //Argument Capturing
        ArgumentCaptor<String> entityArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(view, times(1)).onResponseFailure(entityArgumentCaptor.capture(), eq("REQUEST_TAG_GET_POPULAR_ARTICLE"));
        verify(view).onResponseFailure(entityArgumentCaptor.getValue(), "REQUEST_TAG_GET_POPULAR_ARTICLE");

    }


    /**
     * @return
     */
    private ArticleList getArticles() {
        List<Article> articleList = new ArrayList<>();
        ArticleList articleList1 = new ArticleList();
        Article article = new Article();
        article.setTitle("asdfsd");
        article.setByLine("asdfas");
        article.setPublishedDate("qwerwr");
        articleList.add(article);
        articleList1.setArticleList(articleList);
        return articleList1;
    }
}
