package com.akinsete.nytgithubsearch.ui.search;

import com.akinsete.nytgithubsearch.data.network.model.responses.SearchResponse;
import com.akinsete.nytgithubsearch.rx.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by sundayakinsete on 2019-04-12.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    @Mock
    SearchContract.View mMockSearchView;
    @Mock
    SearchContract.Interactor mMockSearchInteractor;

    private SearchPresenter<SearchContract.View, SearchContract.Interactor> mSearchPresenter;
    private TestScheduler mTestScheduler;

    @Before
    public void setUp() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mSearchPresenter = new SearchPresenter<>(
                mMockSearchInteractor,
                testSchedulerProvider,
                compositeDisposable);
        mSearchPresenter.onAttach(mMockSearchView);
    }


    @Test
    public void searchRepositoryByOrganisation() {
        String searchQuery = "apple";
        int limit = 3;

        SearchResponse searchResponse = new SearchResponse();

        when(mMockSearchInteractor.doSearchRepositoryByOrganisation(searchQuery, limit))
                .thenReturn(Observable.just(searchResponse));

        mSearchPresenter.searchRepository(searchQuery);

        mTestScheduler.triggerActions();

        verify(mMockSearchView, atLeastOnce()).showLoading();
        verify(mMockSearchView, atLeastOnce()).hideLoading();
        verify(mMockSearchView, atLeastOnce()).displaySearchResult(anyList());
    }


    @Test
    public void searchRepositoryByOrganisationShouldFail(){
        String searchQuery = "";

        mSearchPresenter.searchRepository(searchQuery);


        verify(mMockSearchView, atLeastOnce()).showEmptySearchError();

        verify(mMockSearchView, never()).showLoading();
        verify(mMockSearchView, never()).hideLoading();
        verify(mMockSearchView, never()).displaySearchResult(anyList());
    }

    @After
    public void tearDown() {
        mSearchPresenter.onDetach();
    }

}