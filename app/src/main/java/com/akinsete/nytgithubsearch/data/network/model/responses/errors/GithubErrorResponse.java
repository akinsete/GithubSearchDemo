package com.akinsete.nytgithubsearch.data.network.model.responses.errors;

import com.akinsete.nytgithubsearch.data.network.model.responses.errors.abstracts.GithubErrorResponseAbstract;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public class GithubErrorResponse extends GithubErrorResponseAbstract {

    @SerializedName("errors")
    @Expose
    private List<GithubError> errors = null;

    public List<GithubError> getErrors() {
        return errors;
    }

    public void setErrors(List<GithubError> errors) {
        this.errors = errors;
    }
}
