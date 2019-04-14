package com.akinsete.nytgithubsearch.di.module;

import com.akinsete.nytgithubsearch.BuildConfig;
import com.akinsete.nytgithubsearch.data.network.GithubApiHelper;
import com.akinsete.nytgithubsearch.data.network.interceptors.LoggingInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sundayakinsete on 2019-04-10.
 */

@Module
public class NetworkTestModule {

    @Provides
    @Singleton
    OkHttpClient provideClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    @Provides
    @Singleton
    GithubApiHelper provideClientApiHelper(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GithubApiHelper.class);
    }

}
