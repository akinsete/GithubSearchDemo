package com.akinsete.nytgithubsearch.data.network;

import com.akinsete.nytgithubsearch.data.network.model.responses.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by sundayakinsete on 2019-04-10.
 */
public interface GithubApiHelper {

    @POST("v3/session")
    Observable<SearchResponse> searchByOrganisation(String name);

}
