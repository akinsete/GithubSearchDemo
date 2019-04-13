package com.akinsete.nytgithubsearch.data.network.model.responses.errors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public class GithubError {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("field")
    @Expose
    private String field;
    @SerializedName("code")
    @Expose
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
