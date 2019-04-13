package com.akinsete.nytgithubsearch.data.network.model.responses.errors.abstracts;

import com.akinsete.nytgithubsearch.data.network.model.responses.errors.GithubError;

import java.util.List;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public abstract class GithubErrorResponseAbstract {

    public abstract List<GithubError> getErrors();

    public String getErrorMessage() {
        if (getErrors() != null && getErrors().size() > 0){
            return getErrors().get(0).getMessage();
        }
        return "";
    }
}
