package com.udacity.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage1.model.Movie;
import com.udacity.popularmoviesstage1.util.DateAndTimeUtils;
import com.udacity.popularmoviesstage1.util.ImageHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private Movie mMovie;

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    @Bind(R.id.iv_movie_poster)
    ImageView mMoviePosterIV;

    @Bind(R.id.tv_release_date)
    TextView mReleaseDateTV;

    @Bind(R.id.tv_overview_content)
    TextView mOverviewTV;

    @Bind(R.id.rb_1)
    AppCompatRatingBar mMovieRB;

    public static Intent getActivityStartIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initFromIntent(getIntent());
        setTitle(mMovie.getTitle());
        mMovieRB.setRating(mMovie.getVoteAverage());
        mOverviewTV.setText(mMovie.getOverview());
        final String dateString = DateAndTimeUtils.getDateString(mMovie.getReleaseDate());
        if (dateString.length() > 0) {
            mReleaseDateTV.setText(String.format(getString(R.string.sf_release_date), dateString));
        } else {
            mReleaseDateTV.setVisibility(View.GONE);
        }
        ImageHelper.loadImage(mMoviePosterIV, "http://image.tmdb.org/t/p/w500/" + mMovie.getPosterPath());
    }

    private void initFromIntent(Intent intent) {
        mMovie = (Movie) intent.getParcelableExtra(EXTRA_MOVIE);
    }
}
