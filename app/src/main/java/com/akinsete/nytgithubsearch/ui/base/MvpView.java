package com.akinsete.nytgithubsearch.ui.base;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public interface MvpView {

    void showLoading();

    void hideLoading();

    void showError(String message);

    void onError(String message);

}
