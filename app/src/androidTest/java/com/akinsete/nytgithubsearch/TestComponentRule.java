package com.akinsete.nytgithubsearch;

import android.content.Context;

import com.akinsete.nytgithubsearch.di.GithubApplication;
import com.akinsete.nytgithubsearch.di.component.ApplicationComponent;
import com.akinsete.nytgithubsearch.di.component.DaggerApplicationComponent;
import com.akinsete.nytgithubsearch.di.component.TestComponent;
import com.akinsete.nytgithubsearch.di.module.ApplicationModule;
import com.akinsete.nytgithubsearch.di.module.ApplicationTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class TestComponentRule implements TestRule {

    private ApplicationComponent mTestComponent;
    private Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    private void setupDaggerTestComponentInApplication() {
        GithubApplication application = ((GithubApplication) mContext.getApplicationContext());
        mTestComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
        application.setComponent(mTestComponent);
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                } finally {
                    mTestComponent = null;
                }
            }
        };
    }
}
