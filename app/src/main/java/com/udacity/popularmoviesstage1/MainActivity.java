package com.udacity.popularmoviesstage1;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.popularmoviesstage1.dataholder.MovieDataKeepFragment;
import com.udacity.popularmoviesstage1.model.Movie;
import com.udacity.popularmoviesstage1.network.NetworkResponseListener;
import com.udacity.popularmoviesstage1.network.RequestBean;
import com.udacity.popularmoviesstage1.network.ResponseBean;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NetworkResponseListener {

    public static final String TAG_MOVIE_DATA_FRAGMENT = "TAG_MOVIE_DATA_FRAGMENT";
    public String API_KEY;

    @Bind(R.id.tb_action)
    Toolbar mActionBarTB;

    @Bind(R.id.rv_movies_list)
    RecyclerView mMovieListRV;
    private MovieListAdapter adapter;
    private FragmentManager mSupportFragmentManager;
    private GridLayoutManager mGridLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;
    private String mRatingUrl;
    private String mPopularUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        API_KEY = getString(R.string.api_key);
        setSupportActionBar(mActionBarTB);
        setTitle(getString(R.string.string_movies_by_rating));
        mSupportFragmentManager = getSupportFragmentManager();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.grid_column));
        mRatingUrl = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=" + API_KEY;
        mPopularUrl = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + API_KEY;
        adapter = new MovieListAdapter(this, new ArrayList<Movie>(), R.layout.item_movie_grid, new OnMovieItemClickListener() {
            @Override
            public void onMovieClicked(Movie movie) {
                startActivity(MovieDetailActivity.getActivityStartIntent(MainActivity.this, movie));
            }
        });
        adapter.setLayoutRes(R.layout.item_movie_grid);
        mMovieListRV.setAdapter(adapter);
        mMovieListRV.setLayoutManager(mGridLayoutManager);
        makeNetworkRequest(mRatingUrl);
    }

    private void makeNetworkRequest(String url) {
        MovieDataKeepFragment movieDataKeepFragment;
        if (mSupportFragmentManager.findFragmentByTag(TAG_MOVIE_DATA_FRAGMENT) == null) {
            movieDataKeepFragment = MovieDataKeepFragment.newInstance(url, getString(R.string.text_loading_message));
            mSupportFragmentManager.beginTransaction().add(movieDataKeepFragment, TAG_MOVIE_DATA_FRAGMENT).commit();
        } else {
            movieDataKeepFragment = (MovieDataKeepFragment) mSupportFragmentManager.findFragmentByTag(TAG_MOVIE_DATA_FRAGMENT);
        }
        movieDataKeepFragment.setActivityNetworkResponseListenerWeakReference(new WeakReference<NetworkResponseListener>(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mi_sort) {
            if (item.getTitle().equals(getString(R.string.string_popularity))) {
                requestNewDataForDifferentSorting(item, mPopularUrl, R.string.string_rating, R.string.string_movies_by_popularity);
            } else {
                requestNewDataForDifferentSorting(item, mRatingUrl, R.string.string_popularity, R.string.string_movies_by_rating);
            }
            return true;
        }

        else  if (id == R.id.mi_view) {
            if (mMovieListRV.getLayoutManager() instanceof GridLayoutManager) {
                changeViewForMovies(item, R.layout.item_movie_list, mLinearLayoutManager, R.string.string_grid_view);
            } else {
                changeViewForMovies(item, R.layout.item_movie_grid, mGridLayoutManager, R.string.string_list_view);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeViewForMovies(MenuItem item, int item_movie_list, LinearLayoutManager linearLayoutManager, int string_grid_view) {
        adapter.setLayoutRes(item_movie_list);
        mMovieListRV.setLayoutManager(linearLayoutManager);
        mMovieListRV.setAdapter(adapter);
        item.setTitle(string_grid_view);
    }

    private void requestNewDataForDifferentSorting(MenuItem item, String ratingUrl,@StringRes int string_popularity,@StringRes int title) {
//        mSupportFragmentManager.popBackStackImmediate();
        FragmentTransaction trans = mSupportFragmentManager.beginTransaction();
        trans.remove(mSupportFragmentManager.findFragmentByTag(TAG_MOVIE_DATA_FRAGMENT));
        trans.commit();
        mSupportFragmentManager.popBackStackImmediate();
        adapter.clear();
        makeNetworkRequest(ratingUrl);
        item.setTitle(string_popularity);
        setTitle(title);
    }

    @Override
    public void onResponse(ResponseBean responseBean, @RequestBean.RequestType int requestType) {
        if (responseBean != null && responseBean.isSuccess()) {
            List<Movie> movieList = responseBean.getResults();
            if (movieList.size() > 0) {
                adapter.add(movieList);
            } else {
                showError(R.string.error_no_movies);
            }
        } else {
            showError(responseBean.getErrorString());
        }
    }

    private void showError(String errorString) {
        Snackbar.make(mMovieListRV, errorString, Snackbar.LENGTH_SHORT).show();
    }

    private void showError(@StringRes int errorString) {
        Snackbar.make(mMovieListRV, errorString, Snackbar.LENGTH_SHORT).show();
    }
}
