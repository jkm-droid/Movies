package com.jkmdroid.movies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
/**
 * this class creates a custom arrayadapter for displaying custom movies_row_background to the user
 * **/
public class MovieAdapter extends ArrayAdapter<MovieSetterGetter> {

    private Context context;
    private ArrayList<MovieSetterGetter> moviesList;


    MovieAdapter(@NonNull Context context, ArrayList<MovieSetterGetter> moviesList) {
        super(context, R.layout.movies_row, moviesList);

        this.context = context;
        this.moviesList = moviesList;
    }

    /**
     * get the views of the respective elements and set their specific values
     * using the MovieSetterGetter class
     * **/
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(R.layout.movies_row, null);

        MovieSetterGetter movieSetterGetter = moviesList.get(position);

        TextView movie_name, movie_year, movie_duration, movie_rating;
        ImageView movie_banner;


        movie_name = view.findViewById(R.id.movie_name);
        movie_name.setText(movieSetterGetter.getTitle());

        movie_year = view.findViewById(R.id.movie_year);
        String year = movieSetterGetter.getYear();
        movie_year.setText(year);

        movie_duration = view.findViewById(R.id.movie_duration);
        movie_duration.setText(String.format("%s mins", movieSetterGetter.getDuration()));

        movie_rating = view.findViewById(R.id.movie_rating);
        movie_rating.setText(String.format("%s ", movieSetterGetter.getRating()));

        /**
         * changing the rating bar depending on the values of the
         * rating per movie
         * **/
        RatingBar ratingBar = view.findViewById(R.id.rating_bar);
        String s_rating  = movieSetterGetter.getRating();
        double rating = Double.parseDouble(s_rating);

        if (rating >= 8.0){
            ratingBar.setRating((float) 3.5);
        }else{
            ratingBar.setRating((float) 2.5);
        }


        movie_banner = view.findViewById(R.id.movie_banner);

        Picasso.get()
                .load(movieSetterGetter.getMovie_poster())
                .resize(90,90)
                .centerCrop()
                .into(movie_banner);


        return view;
    }
}
