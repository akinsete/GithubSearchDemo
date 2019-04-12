package com.akinsete.nytgithubsearch.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.akinsete.nytgithubsearch.R;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity {

    @Inject
    SearchContract.Presenter<SearchContract.View, SearchContract.Interactor> mSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getActivityComponent().inject(this);

    }
}
