package com.nytimes.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nytimes.R;
import com.nytimes.model.Article;
import com.nytimes.viewlayer.RecyclerItemClickListener;

import java.util.List;

/**
 * Created by anshulsachdeva on 16/03/19.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> articleList;
    private RecyclerItemClickListener recyclerItemClickListener;

    public ArticleAdapter(List<Article> dataList , RecyclerItemClickListener recyclerItemClickListener) {
        this.articleList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_row, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvTitle.setText(articleList.get(position).getTitle());
        holder.tvByLine.setText(articleList.get(position).getByLine());
        holder.tvDate.setText(articleList.get(position).getPublishedDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(articleList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvByLine, tvDate;

        ArticleViewHolder(View itemView) {
            super(itemView);
            tvTitle=  itemView.findViewById(R.id.tv_title);
            tvByLine =  itemView.findViewById(R.id.tv_byline);
            tvDate =  itemView.findViewById(R.id.tv_date);

        }
    }
}