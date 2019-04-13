package com.akinsete.nytgithubsearch.ui.search;

import com.akinsete.nytgithubsearch.data.network.GithubApiHelper;
import com.akinsete.nytgithubsearch.data.network.model.responses.SearchResponse;
import com.akinsete.nytgithubsearch.ui.base.BaseInteractor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public class SearchInteractor extends BaseInteractor implements SearchContract.Interactor {

    @Inject
    SearchInteractor(GithubApiHelper clientApiHelper) {
        super(clientApiHelper);
    }

    @Override
    public Observable<SearchResponse> doSearchRepositoryByOrganisation(String organisationName) {
        return getGithubApiHelper().searchByOrganisation(organisationName);
    }
}
