package com.akinsete.nytgithubsearch.di;

import android.app.Application;

import com.akinsete.nytgithubsearch.di.component.ApplicationComponent;
import com.akinsete.nytgithubsearch.di.component.DaggerApplicationComponent;
import com.akinsete.nytgithubsearch.di.module.ApplicationModule;

/**
 * Created by sundayakinsete on 2019-04-10.
 */
public class GithubApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }


    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
