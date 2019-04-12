package com.akinsete.nytgithubsearch.ui.base;

import com.akinsete.nytgithubsearch.data.network.GithubApiHelper;

import javax.inject.Inject;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public class BaseInteractor implements  MvpInteractor {

    private final GithubApiHelper mClientApiHelper;

    @Inject
    public BaseInteractor(GithubApiHelper clientApiHelper) {
        this.mClientApiHelper = clientApiHelper;
    }

    @Override
    public GithubApiHelper getGithubApiHelper() {
        return mClientApiHelper;
    }
}
