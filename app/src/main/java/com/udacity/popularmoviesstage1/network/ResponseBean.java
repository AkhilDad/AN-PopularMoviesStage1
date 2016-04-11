package com.udacity.popularmoviesstage1.network;

import com.google.gson.annotations.SerializedName;
import com.udacity.popularmoviesstage1.model.Movie;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by akhil on 27/02/16.
 */
public class ResponseBean {

    private int mResponseCode;
    private String mErrorString;

    @SerializedName("results")
    private List<Movie> mResults;

    @SerializedName("page")
    private int mPage;

    @SerializedName("total_count")
    private int mTotalCount;

    @SerializedName("total_pages")
    private int mToatalPages;

    public List<Movie> getResults() {
        return mResults;
    }

    public void setResults(List<Movie> results) {
        mResults = results;
    }

    public boolean isSuccess() {
        return mResponseCode == HttpURLConnection.HTTP_OK;
    }

    public int getResponseCode() {
        return mResponseCode;
    }

    public void setResponseCode(int responseCode) {
        mResponseCode = responseCode;
    }

    public String getErrorString() {
        return mErrorString;
    }

    public void setErrorString(String errorString) {
        mErrorString = errorString;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(int totalCount) {
        mTotalCount = totalCount;
    }

    public int getToatalPages() {
        return mToatalPages;
    }

    public void setToatalPages(int toatalPages) {
        mToatalPages = toatalPages;
    }
}
