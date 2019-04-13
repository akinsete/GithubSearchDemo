package com.akinsete.nytgithubsearch.ui.search;


import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.akinsete.nytgithubsearch.R;
import com.akinsete.nytgithubsearch.data.network.model.responses.Repo;
import com.akinsete.nytgithubsearch.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import javax.inject.Inject;

    public class SearchActivity extends BaseActivity implements SearchContract.View {

    @Inject
    SearchContract.Presenter<SearchContract.View, SearchContract.Interactor> mSearchPresenter;

    @BindView(R.id.et_search) EditText editTextSearch;
    @BindView(R.id.search_recycler_view) RecyclerView recyclerViewSearch;

    private RepoListAdapter mRepoListAdapter;
    private List<Repo> mRepos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        getActivityComponent().inject(this);

        ButterKnife.bind(this);

        setupRecyclerView();

        mSearchPresenter.onAttach(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRepoListAdapter = new RepoListAdapter(mRepos, url -> {
            mNavigator.openRepoUrl(this, url);
        });

        recyclerViewSearch.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerViewSearch.getContext(),
                linearLayoutManager.getOrientation());
        recyclerViewSearch.addItemDecoration(itemDecoration);
        recyclerViewSearch.setAdapter(mRepoListAdapter);
    }

    @OnClick(R.id.btn_search)
    public void btnSearchClicked(){
        mRepoListAdapter.resetData();
        mSearchPresenter.searchRepository(editTextSearch.getText().toString());
    }

    @Override
    public void showEmptySearchQueryError() {
        showError(getString(R.string.empty_org_error));
    }

    @Override
    public void showEmptySearchResultError() {
        showError(getString(R.string.empty_search_result) + editTextSearch.getText().toString());
    }

    @Override
    public void displaySearchResult(List<Repo> repos) {
        hideKeyboard(this.getCurrentFocus());
        mRepoListAdapter.updateData(repos);
    }

    @Override
    protected void onDestroy() {
        mSearchPresenter.onDetach();
        super.onDestroy();
    }



}
