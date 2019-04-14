package com.akinsete.nytgithubsearch.di.component;

import com.akinsete.nytgithubsearch.di.module.ApplicationTestModule;
import com.akinsete.nytgithubsearch.di.module.NetworkTestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sundayakinsete on 2019-04-13.
 */
@Singleton
@Component(modules = {ApplicationTestModule.class, NetworkTestModule.class})
public interface TestComponent extends ApplicationComponent {
}
