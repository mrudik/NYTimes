package com.mrudik.nytimes.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mrudik.nytimes.NewsApp;
import com.mrudik.nytimes.R;
import com.mrudik.nytimes.db.entity.Article;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements NewsContract.View {
    private final static String TAG = "NewsActivityTag";

    @Inject
    NewsPresenter presenter;

    @BindView(R.id.news_list)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        NewsApp app = (NewsApp) getApplication();
        app.getAppComponent().createNewsSubComponent().inject(this);

        getLifecycle().addObserver(presenter);

        initRecyclerView();

        presenter.setView(this);
        presenter.loadNews();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "Show loading");
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "Hide loading");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNews(final List<Article> newsList) {
        Log.d(TAG, "Show news");
        recyclerView.setAdapter(new NewsAdapter(this, newsList));
    }

    @Override
    public void showError(String errorMessage) {
        Log.d(TAG, "Error message is: " + errorMessage);
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}