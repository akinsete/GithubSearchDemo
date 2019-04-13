
package com.akinsete.nytgithubsearch.data.network.model.responses;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("items")
    @Expose
    private List<Repo> items = new ArrayList<>();

    public List<Repo> getRepos() {
        return items;
    }

    public void setRepos(List<Repo> repos) {
        this.items = repos;
    }

}
