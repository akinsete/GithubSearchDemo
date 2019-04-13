package com.akinsete.nytgithubsearch.ui.base;

import android.util.Log;

import com.akinsete.nytgithubsearch.data.network.model.responses.errors.GithubErrorResponse;
import com.akinsete.nytgithubsearch.uitls.rx.SchedulerProvider;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public class BasePresenter<V extends MvpView, I extends MvpInteractor>
        implements MvpPresenter<V, I> {

    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;
    private I mMvpInteractor;

    @Inject
    public BasePresenter(I mvpInteractor,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        mMvpInteractor = mvpInteractor;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
        mMvpInteractor = null;
    }

    @Override
    public V getMvpView() {
        return mMvpView;
    }

    @Override
    public I getInteractor() {
        return mMvpInteractor;
    }


    @Override
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public void handleServerError(Throwable throwable) {

        if (!isViewAttached()) {
            return;
        }

        getMvpView().hideLoading();

        if (throwable instanceof HttpException) {

            //((HttpException) throwable).code()
            // We can write a switch statement here to handle different error code
            HttpException b = ((HttpException) throwable);

            ResponseBody errorBody = b.response().errorBody();

            if (errorBody != null) {
                /// Try to parse github custom error message
                try {
                    String error = errorBody.string();
                    GithubErrorResponse githubErrorResponse = new Gson().fromJson(error,
                            GithubErrorResponse.class);
                    getMvpView().onError(githubErrorResponse.getErrorMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                getMvpView().onError(b.message());
            }

        }

    }

    @Override
    public void checkViewAttached() throws MvpViewNotAttachedException {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    protected SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
