package com.udacity.popularmoviesstage1.util;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage1.R;

/**
 * Created by akhil on 08/04/16.
 */
public class ImageHelper {
    private ImageHelper() {

    }

    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.with(imageView.getContext()).load(imageUrl).error(R.drawable.no_image)
                .placeholder(R.drawable.progress_animation).into(imageView);

    }
}
