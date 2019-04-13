package com.akinsete.nytgithubsearch.ui.search;

import com.akinsete.nytgithubsearch.data.network.model.responses.SearchResponse;
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
    SearchPresenter(I mvpInteractor, SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void searchRepository(String organisationName) {

        if (organisationName == null || organisationName.isEmpty()) {
            getMvpView().showEmptySearchError();
            return;
        }

        getMvpView().showLoading();

        getCompositeDisposable().add(getInteractor()
                .doSearchRepositoryByOrganisation(organisationName)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(searchResponse -> {

                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    getMvpView().displaySearchResult(null);

                }, this::handleServerError)
        );
    }


}
