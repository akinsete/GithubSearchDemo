package com.akinsete.nytgithubsearch.di.module;

import android.content.Context;

import com.akinsete.nytgithubsearch.di.ActivityContext;
import com.akinsete.nytgithubsearch.di.PerActivity;
import com.akinsete.nytgithubsearch.navigator.Navigator;
import com.akinsete.nytgithubsearch.ui.search.SearchContract;
import com.akinsete.nytgithubsearch.ui.search.SearchInteractor;
import com.akinsete.nytgithubsearch.ui.search.SearchPresenter;
import com.akinsete.nytgithubsearch.uitls.rx.AppSchedulerProvider;
import com.akinsete.nytgithubsearch.uitls.rx.SchedulerProvider;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sundayakinsete on 2019-04-10.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @PerActivity
    SearchContract.Presenter<SearchContract.View, SearchContract.Interactor> provideSearchPresenter(
            SearchPresenter<SearchContract.View, SearchContract.Interactor> presenter) {
        return presenter;
    }
    
    @Provides
    @PerActivity
    SearchContract.Interactor provideSearchContractInteractor(SearchInteractor interactor) {
        return interactor;
    }

}
