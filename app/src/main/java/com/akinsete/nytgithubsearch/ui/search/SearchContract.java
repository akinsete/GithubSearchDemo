package com.akinsete.nytgithubsearch.ui.search;

import com.akinsete.nytgithubsearch.data.network.model.responses.SearchResponse;
import com.akinsete.nytgithubsearch.data.network.model.responses.SearchResult;
import com.akinsete.nytgithubsearch.di.PerActivity;
import com.akinsete.nytgithubsearch.ui.base.MvpInteractor;
import com.akinsete.nytgithubsearch.ui.base.MvpPresenter;
import com.akinsete.nytgithubsearch.ui.base.MvpView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public interface SearchContract {

    interface View extends MvpView {

        void displaySaearchResult(List<SearchResult> searchResult);

    }

    @PerActivity
    interface Presenter<V extends SearchContract.View,
            I extends SearchContract.Interactor> extends MvpPresenter<V, I> {

        void searchRepository(String organisationName);
    }


    interface Interactor extends MvpInteractor {

        Observable<SearchResponse> doSearchRepositoryByOrganisation(String organisationName);
    }
}
