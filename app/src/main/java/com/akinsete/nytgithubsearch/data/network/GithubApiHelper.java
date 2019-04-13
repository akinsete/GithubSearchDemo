package com.akinsete.nytgithubsearch.data.network;

import com.akinsete.nytgithubsearch.data.network.model.responses.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sundayakinsete on 2019-04-10.
 */
public interface GithubApiHelper {

    @GET("search/repositories")
    Observable<SearchResponse> searchByOrganisation(@Query("q") String orgQuery,
                                                    @Query("sort") String sortBy,
                                                    @Query("order") String orderBy,
                                                    @Query("per_page") int limit);

}
