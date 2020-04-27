package com.jkmdroid.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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


        latest_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button_name = latest_movies.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                intent.putExtra("BUTTON_NAME", button_name);
                startActivity(intent);
            }
        });

        top_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button_name = top_movies.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                intent.putExtra("BUTTON_NAME", button_name);
                startActivity(intent);
            }
        });

        action_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button_name = action_movies.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                intent.putExtra("BUTTON_NAME", button_name);
                startActivity(intent);
            }
        });

        romantic_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button_name = romantic_movies.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                intent.putExtra("BUTTON_NAME", button_name);
                startActivity(intent);
            }
        });

        scifi_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button_name = scifi_movies.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                intent.putExtra("BUTTON_NAME", button_name);
                startActivity(intent);
            }
        });

        random_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button_name = random_movies.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                intent.putExtra("BUTTON_NAME", button_name);
                startActivity(intent);
            }
        });

        animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button_name = animation.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                intent.putExtra("BUTTON_NAME", button_name);
                startActivity(intent);
            }
        });

        horror_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String button_name = horror_movies.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MoviesPage.class);
                intent.putExtra("BUTTON_NAME", button_name);
                startActivity(intent);
            }
        });


    }
}
