package com.akinsete.nytgithubsearch.rx;

import com.akinsete.nytgithubsearch.uitls.rx.SchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by sundayakinsete on 2019-02-14.
 */
public class TestSchedulerProvider implements SchedulerProvider {

    private final TestScheduler mTestScheduler;

    public TestSchedulerProvider(TestScheduler testScheduler) {
        mTestScheduler = testScheduler;
    }

    @Override
    public Scheduler ui() {
        return mTestScheduler;
    }

    @Override
    public Scheduler computation() {
        return mTestScheduler;
    }

    @Override
    public Scheduler io() {
        return mTestScheduler;
    }

}
