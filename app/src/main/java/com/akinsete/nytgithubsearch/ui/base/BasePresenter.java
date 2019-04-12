package com.akinsete.nytgithubsearch.ui.base;

import com.akinsete.nytgithubsearch.uitls.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

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
