package com.udacity.popularmoviesstage1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.popularmoviesstage1.model.Movie;

import java.util.List;

/**
 * Created by akhil on 01/03/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Context mContext;
    private final List<Movie> mMovieList;
    private OnMovieItemClickListener mOnMovieItemClickListener;
    private int mLayoutRes;

    public MovieListAdapter(Context context, List<Movie> movieList, int layoutRes, OnMovieItemClickListener onMovieItemClickListener) {

        mContext = context;
        mMovieList = movieList;
        mLayoutRes = layoutRes;
        mOnMovieItemClickListener = onMovieItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(mContext).inflate(mLayoutRes, parent, false), mOnMovieItemClickListener);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder )holder).bind(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public void add(List<Movie> movieList) {
        int size = getItemCount();
        mMovieList.addAll(movieList);
        notifyItemInserted(size);
    }

    public void clear() {
        mMovieList.clear();
        notifyDataSetChanged();
    }

    public void setLayoutRes(int layoutRes) {
        mLayoutRes = layoutRes;
    }
}
