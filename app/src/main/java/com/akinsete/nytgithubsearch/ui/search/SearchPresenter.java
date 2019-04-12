package com.akinsete.nytgithubsearch.ui.search;

import com.akinsete.nytgithubsearch.ui.base.BasePresenter;
import com.akinsete.nytgithubsearch.uitls.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public class SearchPresenter<V extends SearchContract.View, I extends SearchContract.Interactor>
        extends BasePresenter<V, I> implements SearchContract.Presenter<V, I> {

    @Inject
    public SearchPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void searchRepository(String organisationName) {

    }


}
