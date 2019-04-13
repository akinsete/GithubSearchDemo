package com.akinsete.nytgithubsearch.ui.search;


import android.os.Bundle;
import android.widget.EditText;

import com.akinsete.nytgithubsearch.R;
import com.akinsete.nytgithubsearch.data.network.model.responses.SearchResult;
import com.akinsete.nytgithubsearch.ui.base.BaseActivity;

import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    @Inject
    SearchContract.Presenter<SearchContract.View, SearchContract.Interactor> mSearchPresenter;

    @BindView(R.id.et_search) EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        getActivityComponent().inject(this);

        mSearchPresenter.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick
    public void btnSearchClicked(){
        mSearchPresenter.searchRepository(editTextSearch.getText().toString());
    }

    @Override
    public void showEmptySearchError() {
        showError(getString(R.string.empty_org_error));
    }

    @Override
    public void displaySearchResult(List<SearchResult> searchResult) {

    }

    @Override
    protected void onDestroy() {
        mSearchPresenter.onDetach();
        super.onDestroy();
    }



}
