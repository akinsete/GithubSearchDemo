package com.akinsete.nytgithubsearch.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.akinsete.nytgithubsearch.di.GithubApplication;
import com.akinsete.nytgithubsearch.di.component.ActivityComponent;
import com.akinsete.nytgithubsearch.di.component.DaggerActivityComponent;
import com.akinsete.nytgithubsearch.di.module.ActivityModule;
import com.akinsete.nytgithubsearch.navigator.Navigator;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public class BaseActivity extends AppCompatActivity implements MvpView{

    @Inject
    protected Navigator mNavigator;

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((GithubApplication) getApplication()).getComponent())
                .build();
    }


    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {

    }
}
