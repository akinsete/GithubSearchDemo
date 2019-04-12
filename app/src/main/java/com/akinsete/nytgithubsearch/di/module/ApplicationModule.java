package com.akinsete.nytgithubsearch.di.module;


import android.app.Application;
import android.content.Context;

import com.akinsete.nytgithubsearch.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by sundayakinsete on 2019-04-10.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }
}
