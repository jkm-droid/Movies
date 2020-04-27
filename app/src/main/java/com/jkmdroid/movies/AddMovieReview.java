package com.jkmdroid.movies;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMovieReview extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_review);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("MovieReviewer/Add Movie Review");

        Intent intent = getIntent();
        String movie_title =  intent.getStringExtra("MOVIE_TITLE");
        TextView m_title = findViewById(R.id.textview_movie_title);
        m_title.setText(movie_title);

        Button submit_review = findViewById(R.id.button_submit);
        submit_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit_movie_review();
            }
        });

    }

    private void submit_movie_review() {

        EditText edittext_review, edittext_rating;
        edittext_review = findViewById(R.id.edittext_review);
        edittext_rating = findViewById(R.id.edittext_rating);

        String movie_review = edittext_review.getText().toString();
        String movie_rating = edittext_rating.getText().toString();
        float rating = Float.parseFloat(movie_rating);

        if (movie_review.isEmpty()){

            Toast.makeText(getApplicationContext(), "You cannot submit an empty review", Toast.LENGTH_SHORT).show();
        }

        if (movie_rating.isEmpty()){

            Toast.makeText(getApplicationContext(), "You cannot submit an empty rating", Toast.LENGTH_SHORT).show();
        }

        if (Float.parseFloat(movie_rating) < 1){

            Toast.makeText(getApplicationContext(), "Your rating must be more than 0", Toast.LENGTH_SHORT).show();
        }

        if ( Float.parseFloat(movie_rating) > 10){

            Toast.makeText(getApplicationContext(), "Your rating must be less than 10", Toast.LENGTH_SHORT).show();

        }

        if (!movie_review.isEmpty() && !movie_rating.isEmpty()){

            if (rating >= 1 && rating < 10){

                System.out.println("---------------------"+movie_review+movie_rating+"------------------------");
                Toast.makeText(getApplicationContext(), "Everything is okay", Toast.LENGTH_SHORT).show();

            }
        }

    }
}
