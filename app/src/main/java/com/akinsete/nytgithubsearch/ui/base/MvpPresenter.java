package com.akinsete.nytgithubsearch.ui.base;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public interface MvpPresenter<V extends MvpView, I extends MvpInteractor> {

    void onAttach(V mvpView);

    void onDetach();

    V getMvpView();

    I getInteractor();

    boolean isViewAttached();

    void handleServerError(Throwable throwable);

    void checkViewAttached() throws BasePresenter.MvpViewNotAttachedException;

}
