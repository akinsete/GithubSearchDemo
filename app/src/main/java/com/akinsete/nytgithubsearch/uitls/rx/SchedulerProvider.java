package com.akinsete.nytgithubsearch.uitls.rx;

import io.reactivex.Scheduler;

/**
 * Created by sundayakinsete on 8/02/2019.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
