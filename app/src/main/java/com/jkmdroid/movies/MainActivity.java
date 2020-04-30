package com.jkmdroid.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    Button latest_movies,top_movies, action_movies;
    Button romantic_movies, scifi_movies, random_movies, animation, horror_movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latest_movies = findViewById(R.id.latest_movies);
        top_movies = findViewById(R.id.top_movies);
        action_movies = findViewById(R.id.action_movies);
        romantic_movies = findViewById(R.id.romantic_movies);
        scifi_movies = findViewById(R.id.scifi_movies);
        random_movies = findViewById(R.id.random_movies);
        animation = findViewById(R.id.animation_movies);
        horror_movies = findViewById(R.id.horror_movies);

        final CheckNetworkStatus networkStatus = new CheckNetworkStatus();

        latest_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    String button_name = latest_movies.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                    intent.putExtra("BUTTON_NAME", button_name);
                    startActivity(intent);
                }else{
                    //show an error message
                    setSnackbar();
                }
            }
        });

        top_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    String button_name = top_movies.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                    intent.putExtra("BUTTON_NAME", button_name);
                    startActivity(intent);
                }else{

                    setSnackbar();
                }
            }
        });

        action_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    String button_name = action_movies.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                    intent.putExtra("BUTTON_NAME", button_name);
                    startActivity(intent);
                }else {
                    setSnackbar();
                }
            }
        });

        romantic_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    String button_name = romantic_movies.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                    intent.putExtra("BUTTON_NAME", button_name);
                    startActivity(intent);
                }else{

                    setSnackbar();
                }
            }
        });

        scifi_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    String button_name = scifi_movies.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                    intent.putExtra("BUTTON_NAME", button_name);
                    startActivity(intent);
                }else{

                    setSnackbar();
                }
            }
        });

        random_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    String button_name = random_movies.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                    intent.putExtra("BUTTON_NAME", button_name);
                    startActivity(intent);
                }else{

                   setSnackbar();
                }
            }
        });

        animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    String button_name = animation.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                    intent.putExtra("BUTTON_NAME", button_name);
                    startActivity(intent);
                }else{

                    setSnackbar();
                }
            }
        });

        horror_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    String button_name = horror_movies.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                    intent.putExtra("BUTTON_NAME", button_name);
                    startActivity(intent);
                }else{

                    setSnackbar();
                }
            }
        });


    }

    /**
     * method for showing the snack bar with error message if not internet
     * connection is available
     * **/
    private void setSnackbar() {

        Snackbar snackbar = Snackbar.make(findViewById(R.id.relativelayout_main), "No internet connection", Snackbar.LENGTH_SHORT);
        View snackbarview = snackbar.getView();
        snackbarview.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }
}
