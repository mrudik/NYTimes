package com.mrudik.nytimes.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrudik.nytimes.R;
import com.mrudik.nytimes.db.entity.Article;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final WeakReference<Context> contextRef;
    private final List<Article> itemsList;

    NewsAdapter(Context context, List<Article> itemsList) {
        this.contextRef = new WeakReference<>(context);
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contextRef.get());
        return new ViewHolder(
                inflater.inflate(
                        R.layout.item_article,
                        null
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setNewsData(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView titleTextView;
        @BindView(R.id.description)
        TextView descriptionTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setNewsData(@NonNull Article article) {
            this.titleTextView.setText(article.getTitle());
            this.descriptionTextView.setText(article.getDescription());
        }
    }
}