package com.udacity.popularmoviesstage1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by akhil on 28/02/16.
 */
public class Movie implements Parcelable {

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("adult")
    private boolean mIsAdult;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private Date mReleaseDate;

    @SerializedName("genre_ids")
    private List<Long> mGenreIds;

    @SerializedName("id")
    private long mId;

    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("original_language")
    private String mOriginalLanguage;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("popularity")
    private float mPopularity;

    @SerializedName("vote_count")
    private int mVoteCount;

    @SerializedName("video")
    private boolean mIsVideo;

    @SerializedName("vote_average")
    private float mVoteAverage;

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public boolean isAdult() {
        return mIsAdult;
    }

    public void setIsAdult(boolean isAdult) {
        mIsAdult = isAdult;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Date getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        mReleaseDate = releaseDate;
    }

    public List<Long> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        mGenreIds = genreIds;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public void setPopularity(float popularity) {
        mPopularity = popularity;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }

    public boolean isVideo() {
        return mIsVideo;
    }

    public void setIsVideo(boolean isVideo) {
        mIsVideo = isVideo;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        mVoteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPosterPath);
        dest.writeByte(mIsAdult ? (byte) 1 : (byte) 0);
        dest.writeString(this.mOverview);
        dest.writeLong(mReleaseDate != null ? mReleaseDate.getTime() : -1);
        dest.writeList(this.mGenreIds);
        dest.writeLong(this.mId);
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOriginalLanguage);
        dest.writeString(this.mTitle);
        dest.writeString(this.mBackdropPath);
        dest.writeFloat(this.mPopularity);
        dest.writeInt(this.mVoteCount);
        dest.writeByte(mIsVideo ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.mVoteAverage);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.mPosterPath = in.readString();
        this.mIsAdult = in.readByte() != 0;
        this.mOverview = in.readString();
        long tmpMReleaseDate = in.readLong();
        this.mReleaseDate = tmpMReleaseDate == -1 ? null : new Date(tmpMReleaseDate);
        this.mGenreIds = new ArrayList<Long>();
        in.readList(this.mGenreIds, Long.class.getClassLoader());
        this.mId = in.readLong();
        this.mOriginalTitle = in.readString();
        this.mOriginalLanguage = in.readString();
        this.mTitle = in.readString();
        this.mBackdropPath = in.readString();
        this.mPopularity = in.readFloat();
        this.mVoteCount = in.readInt();
        this.mIsVideo = in.readByte() != 0;
        this.mVoteAverage = in.readFloat();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
