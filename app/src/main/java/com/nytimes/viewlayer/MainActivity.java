package com.nytimes.viewlayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nytimes.R;
import com.nytimes.adapter.ArticleAdapter;
import com.nytimes.contract.AppContract.ArticleView;
import com.nytimes.contract.AppContract.Presenter;
import com.nytimes.interactor.DataInteractorImpl;
import com.nytimes.model.Article;
import com.nytimes.model.ArticleList;
import com.nytimes.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshulsachdeva on 15/03/19.
 */


public class MainActivity extends AppCompatActivity implements ArticleView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        initProgressBar();
        configPresenter();
    }

    /**
     *
     */
    private void configPresenter() {
        presenter = new PresenterImpl(this, new DataInteractorImpl());
        presenter.requestDataFromServer();
    }


    /**
     * Initializing Toolbar and RecyclerView
     */
    private void initializeToolbarAndRecyclerView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view_employee_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

    }


    /**
     * Initializing progressbar programmatically
     */
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }


    /**
     * RecyclerItem click event listener
     */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Article article) {

            Toast.makeText(MainActivity.this, "Title : " + article.getTitle(),
                    Toast.LENGTH_LONG).show();

        }
    };


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setContentToView(ArticleList articleList) {

        List<Article> dataList = null;
        if (articleList == null || articleList.getArticleList() == null) {
            dataList = new ArrayList<>();
        } else {
            dataList = articleList.getArticleList();
        }
        ArticleAdapter adapter = new ArticleAdapter(dataList, recyclerItemClickListener);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(String message, String tag) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + message,
                Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


}

