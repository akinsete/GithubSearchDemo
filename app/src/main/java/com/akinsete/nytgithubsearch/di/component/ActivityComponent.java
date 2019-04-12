package com.akinsete.nytgithubsearch.di.component;

import com.akinsete.nytgithubsearch.di.PerActivity;
import com.akinsete.nytgithubsearch.di.module.ActivityModule;
import com.akinsete.nytgithubsearch.ui.search.SearchActivity;

import dagger.Component;

/**
 * Created by sundayakinsete on 2019-04-10.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SearchActivity searchActivity);
}
