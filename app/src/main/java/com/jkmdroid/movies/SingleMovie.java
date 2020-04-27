package com.jkmdroid.movies;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

/**
 * this class receives specific movie data from the MoviesPage class and
 * renders it to the user
 * **/
public class SingleMovie extends AppCompatActivity {

    String movie_title;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);
        /**
         * initialize all the textviews from the activity
         * **/
        TextView title,year,duration,rating,votes,metascore, gross, story, genre, synopsis;
        ImageView movie_banner = findViewById(R.id.imageViewMovie);

        title = findViewById(R.id.textview_title);
        year = findViewById(R.id.textview_year);
        duration = findViewById(R.id.textview_duration);
        rating = findViewById(R.id.textview_rating);
        votes  = findViewById(R.id.textview_votes);
        metascore = findViewById(R.id.textview_metascore);
        gross = findViewById(R.id.textview_gross);
        genre = findViewById(R.id.textview_genre);
        story = findViewById(R.id.textview_movie_story);
        synopsis = findViewById(R.id.textview_synopsis);

        /**
         * receive all the data from the MoviesPage class
         * **/
        Intent intent = getIntent();
        String movie_year, movie_duration, movie_rating,movie_votes;
        String movie_gross, movie_metascore, movie_genre, movie_story, movie_image_url;

        movie_title = intent.getStringExtra("MOVIE_TITLE");
        movie_year = intent.getStringExtra("MOVIE_YEAR");
        movie_duration = intent.getStringExtra("MOVIE_DURATION");
        movie_rating = intent.getStringExtra("MOVIE_RATING");
        movie_votes = intent.getStringExtra("MOVIE_VOTES");
        movie_metascore = intent.getStringExtra("MOVIE_METASCORE");
        movie_gross = intent.getStringExtra("MOVIE_GROSS");
        movie_genre = intent.getStringExtra("MOVIE_GENRE");
        movie_image_url = intent.getStringExtra("MOVIE_POSTER");
        movie_story = intent.getStringExtra("MOVIE_STORY");

        /**
         * set the action bar to the MovieReviewer/ActivityName
         * */
        CharSequence activity_title = intent.getCharSequenceExtra("ACTIVITY_TITLE");
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("MovieReviewer/"+activity_title);

        /**
         * get the ratingbar from the activity and set a new
         * rating depending on the current rating
         * **/
        assert movie_rating != null;
        double rating_no = Double.parseDouble(movie_rating);
        RatingBar ratingBar = findViewById(R.id.rating_bar_single);

        if (rating_no >= 8){

            ratingBar.setRating((float) 3.5);

        }else{

            ratingBar.setRating((float) 2.5);
        }


        /**
         * append the strings to the individual textviews
         * **/
        title.setText(movie_title);
        year.setText("Release: "+movie_year);
        duration.setText("Time: "+movie_duration+"min");
        rating.setText(movie_rating);
        votes.setText("Votes: "+movie_votes);
        metascore.setText("Score: "+movie_metascore+"/100");
        gross.setText("Gross: $"+movie_gross+"M");
        movie_banner.setImageResource(R.drawable.ic_single_movie_banner);
        genre.setText(movie_genre);
        synopsis.setText("Movie Synopsis");
        story.setText(movie_story);

        /**
         * loading into the view using picasso library
         * */
        Picasso.get()
                .load(movie_image_url)
                .into(movie_banner);


        Button add_movie_review = findViewById(R.id.button_addreview);
        add_movie_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                move_to_add_review_activity();
            }
        });

    }

    private void move_to_add_review_activity() {

        //move to the AddMovieReview class and pass the movie title
        Intent intent = new Intent(SingleMovie.this, AddMovieReview.class);
        intent.putExtra("MOVIE_TITLE", movie_title);
        startActivity(intent);

    }
}