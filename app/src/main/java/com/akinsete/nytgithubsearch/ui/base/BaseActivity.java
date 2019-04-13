package com.akinsete.nytgithubsearch.ui.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akinsete.nytgithubsearch.R;
import com.akinsete.nytgithubsearch.di.GithubApplication;
import com.akinsete.nytgithubsearch.di.component.ActivityComponent;
import com.akinsete.nytgithubsearch.di.component.DaggerActivityComponent;
import com.akinsete.nytgithubsearch.di.module.ActivityModule;
import com.akinsete.nytgithubsearch.navigator.Navigator;
import com.akinsete.nytgithubsearch.uitls.CommonUtils;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Created by sundayakinsete on 2019-04-12.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements MvpView {

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
    public void showError(String message) {
        hideLoading();
        showSnackBar(message);
    }

    @Override
    public void onError(String message) {
        hideLoading();
        showSnackBar(message);
    }

    protected void hideKeyboard(View view) {
        if (view == null) {
            return;
        }
        CommonUtils.hideKeyboard(this, view);
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

}
