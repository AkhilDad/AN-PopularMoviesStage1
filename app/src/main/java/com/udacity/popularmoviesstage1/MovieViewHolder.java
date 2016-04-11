package com.udacity.popularmoviesstage1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage1.model.Movie;
import com.udacity.popularmoviesstage1.util.DateAndTimeUtils;
import com.udacity.popularmoviesstage1.util.ImageHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by akhil on 01/03/16.
 */

//"w92", "w154", "w185", "w342", "w500", "w780", or "original"
public class MovieViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.iv_movie_poster)
    ImageView mMoviePosterIV;

    @Bind(R.id.tv_movie_name)
    TextView mMovieNameTV;
    private OnMovieItemClickListener mOnMovieItemClickListener;

    public MovieViewHolder(View view, OnMovieItemClickListener onMovieItemClickListener) {
        super(view);
        mOnMovieItemClickListener = onMovieItemClickListener;
        ButterKnife.bind(this, view);
    }

    public void bind(final Movie movie) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnMovieItemClickListener.onMovieClicked(movie);
            }
        });
        mMovieNameTV.setText(movie.getTitle());
        ImageHelper.loadImage(mMoviePosterIV, "http://image.tmdb.org/t/p/w342/" + movie.getPosterPath());
    }
}
