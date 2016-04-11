package com.udacity.popularmoviesstage1.dataholder;

import android.os.Bundle;

import com.udacity.popularmoviesstage1.network.NetworkRequest;
import com.udacity.popularmoviesstage1.network.RequestBean;


/**
 * Created by akhil on 02/02/16.
 */
public class MovieDataKeepFragment extends DataKeepFragment{

    public static final String KEY_URL = "key_url";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getArguments().getString(KEY_URL);
        new NetworkRequest(new RequestBean(url, RequestBean.TYPE_ISSUE),this).execute();
        setRetainInstance(true);
    }

    public static MovieDataKeepFragment newInstance(String url, String progressMessage) {
        MovieDataKeepFragment issueDataKeepFragment= new MovieDataKeepFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MovieDataKeepFragment.KEY_URL, url);
        bundle.putString(MovieDataKeepFragment.KEY_PROGRESS_MESSAGE, progressMessage);
        issueDataKeepFragment.setArguments(bundle);
        return issueDataKeepFragment;
    }
}
