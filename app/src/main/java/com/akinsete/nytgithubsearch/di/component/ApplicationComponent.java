package com.akinsete.nytgithubsearch.di.component;

import android.app.Application;
import android.content.Context;

import com.akinsete.nytgithubsearch.data.network.GithubApiHelper;
import com.akinsete.nytgithubsearch.di.ApplicationContext;
import com.akinsete.nytgithubsearch.di.GithubApplication;
import com.akinsete.nytgithubsearch.di.module.ApplicationModule;
import com.akinsete.nytgithubsearch.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sundayakinsete on 2019-04-10.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(GithubApplication app);

    @ApplicationContext
    Context context();

    Application application();

    GithubApiHelper gitubApiHelper();
}
