package com.udacity.popularmoviesstage1.network;

/**
 * Created by akhil on 2/02/16.
 */
public interface NetworkResponseListener {
    void onResponse(ResponseBean responseBean, @RequestBean.RequestType int requestType);
}
