package com.jkmdroid.movies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * this class handles the main logic such as getting movies json and displaying it to the user
 * **/

public class MoviesPage extends AppCompatActivity {
    static Handler handler = new Handler();
    String data;
    String button_latest_movies, button_top_movies, button_action_movies;
    String button_romantic_movies, button_scifi_movies, button_random_movies;
    String button_horror_movies, button_animation;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Intent intent = getIntent();
        button_latest_movies = intent.getStringExtra("BUTTON_NAME");
        button_top_movies = intent.getStringExtra("BUTTON_NAME");
        button_action_movies = intent.getStringExtra("BUTTON_NAME");
        button_romantic_movies = intent.getStringExtra("BUTTON_NAME");
        button_scifi_movies = intent.getStringExtra("BUTTON_NAME");
        button_random_movies = intent.getStringExtra("BUTTON_NAME");
        button_horror_movies = intent.getStringExtra("BUTTON_NAME");
        button_animation = intent.getStringExtra("BUTTON_NAME");


        assert button_latest_movies != null;
        if (button_latest_movies.equals("latest")){

            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle("Latest Movies");
            data = "latest_movies";

        }else if (button_top_movies.equals("top")){

            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle("Top Movies");
            data = "top_movies";

        }else if (button_action_movies.equals("action")){

            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle("Action Movies");
            data = "action_movies";

        }else if(button_romantic_movies.equals("romantic")){

            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle("Romantic Movies");
            data = "romantic_movies";

        }else if(button_scifi_movies.equals("sci-fi")){

            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle("Sci-Fi Movies");
            data = "scifi_movies";

        }else if(button_random_movies.equals("random")){

            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle("Random Movies");
            data = "random_movies";

        }else if (button_animation.equals("animation")){

            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle("Animation");
            data = "animation";

        }else {
            assert button_horror_movies != null;
            if(button_horror_movies.equals("horror")){

                ActionBar actionBar = getSupportActionBar();
                assert actionBar != null;
                actionBar.setTitle("Horror Movies");
                data = "horror_movies";
            }
        }


        /**
         * thread for handling the heavy task of getting movies from server
         * **/
        final CheckNetworkStatus networkStatus = new CheckNetworkStatus();


        final Thread thread = new Thread() {

            @Override
            public void run() {
                super.run();
                /**
                 * first check whether the device has internet connection before sending
                 * request to the server
                 * */
                if(networkStatus.isNetworkAvailable(getApplicationContext())) {

                    try {

                        ProgressBar progressBar;
                        progressBar = findViewById(R.id.progressbar);
                        progressBar.setVisibility(View.VISIBLE);

                        /**
                         * download movies from the link suing the GetOnlineDate class
                         * and one of its method
                         * **/
                        String movies_url = "http://movieapi.mblog.co.ke/getdata.php";
                        String response = GetOnlineData.downloadMovies(movies_url, data);

                        Message msg;
                        msg = handler.obtainMessage();

                        /**
                         * prepare the data and send it to the handle
                         * for display in the movies_row_background
                         * ***/
                        Bundle bundle = new Bundle();
                        bundle.putString("RESPONSE", response);
                        msg.arg1 = 200;//ok message code
                        msg.setData(bundle);
                        handler.sendMessage(msg);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    //send an error message to the handler
                    //when the device has not internet connection
                    Message msg;
                    msg = handler.obtainMessage();
                    msg.arg2 = 300;//error message code
                    handler.sendMessage(msg);
                }

            }
        };
        thread.start();


        /**
         * code logic for handling all the messages from the thread
         * it first checks whether the message code is correct and then
         * gets the data in string format
         * **/

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                if (msg.arg1 == 200){
                    ProgressBar progressBar;
                    progressBar = findViewById(R.id.progressbar);
                    progressBar.setVisibility(View.GONE);
                    Bundle bundle = msg.getData();
                    String response = bundle.getString("RESPONSE");

                    try {
                        /**
                         * get the json object from the link, get the json array inside
                         * the json object
                         * loop through the specific array elements to get them
                         * e.g title, year etc
                         * **/
                        assert response != null;
                        JSONObject moviesObject = new JSONObject(response);
                        JSONArray moviesArray = moviesObject.getJSONArray("movies");

                        int length = moviesArray.length();
                        final ArrayList<MovieSetterGetter> arrayListMovies = new ArrayList<>();
                        MovieSetterGetter movieSetterGetter;
                        int n_votes;
                        int n_metascore;
                        double n_rating;


                        for(int i = 0; i < length; i++){

                            JSONObject jsonObject = moviesArray.getJSONObject(i);

                            movieSetterGetter = new MovieSetterGetter();
                            /**
                             * change the rating, metascore, and votes
                             * */
                            //changing votes
                            String s_votes = jsonObject.getString("votes");
                            int votes = Integer.parseInt(s_votes);

                            if(s_votes.length() <= 5){

                                n_votes = votes+145;

                            }else{

                                n_votes = votes+45;
                            }


                            //change the metascore
                            String s_metascore = jsonObject.getString("metascore");
                            int metascore = Integer.parseInt(s_metascore);

                            if (metascore > 70){
                                n_metascore = metascore - 3;
                            }else {
                                n_metascore = metascore + 3;
                            }

                            //change the rating
                            String s_rating =  jsonObject.getString("rating");
                            double rating = Double.parseDouble(s_rating);
                            DecimalFormat df = new DecimalFormat("#0.0");
                            df.setRoundingMode(RoundingMode.DOWN);


                            if (rating >= 8){
                                n_rating = rating - 0.1;

                            }else {
                                n_rating = rating + 0.1;

                            }

                            movieSetterGetter.setTitle(jsonObject.getString("title"));
                            movieSetterGetter.setYear(jsonObject.getString("year"));
                            movieSetterGetter.setDuration(jsonObject.getString("duration"));
                            movieSetterGetter.setRating(df.format(n_rating));
                            movieSetterGetter.setVotes(String.valueOf(n_votes));
                            movieSetterGetter.setMetascore(String.valueOf(n_metascore));
                            movieSetterGetter.setGross(jsonObject.getString("gross"));
                            movieSetterGetter.setGenre(jsonObject.getString("genre"));
                            movieSetterGetter.setMovie_poster(jsonObject.getString("image"));
                            movieSetterGetter.setStory(jsonObject.getString("story"));

                            arrayListMovies.add(movieSetterGetter);

                        }

                        /**
                         * populate the data into the movies_row_background and render it to
                         * the user
                         * **/
                        ListView listView = findViewById(R.id.listview_movies);
                        MovieAdapter arrayAdapter = new MovieAdapter(MoviesPage.this, arrayListMovies );
                        listView.setAdapter(arrayAdapter);

                        /**
                         * make the movies_row_background clickable
                         * on clicking, direct the user to new activity
                         * pass the specific movie data using intent to the
                         * next activity
                         * */
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                /**
                                 * collect the specific data from the arraylist
                                 * **/
                                String movie_title = arrayListMovies.get(position).getTitle();
                                String movie_year = arrayListMovies.get(position).getYear();
                                String movie_duration = arrayListMovies.get(position).getDuration();
                                String movie_rating = arrayListMovies.get(position).getRating();
                                String movie_votes = arrayListMovies.get(position).getVotes();
                                String movie_metascore = arrayListMovies.get(position).getMetascore();
                                String movie_gross = arrayListMovies.get(position).getGross();
                                String movie_genre = arrayListMovies.get(position).getGenre();
                                String movie_image = arrayListMovies.get(position).getMovie_poster();
                                String movie_story = arrayListMovies.get(position).getStory();

                                /**
                                 * get the activity actionbar
                                 * */

                                ActionBar actionBar = getSupportActionBar();
                                assert actionBar != null;
                                CharSequence action_title = actionBar.getTitle();

                                Intent intent = new Intent(MoviesPage.this, SingleMovie.class);
                                /**
                                 * pass the data to the next activity
                                 * **/
                                intent.putExtra("ACTIVITY_TITLE", action_title);
                                intent.putExtra("MOVIE_TITLE", movie_title);
                                intent.putExtra("MOVIE_YEAR", movie_year);
                                intent.putExtra("MOVIE_DURATION", movie_duration);
                                intent.putExtra("MOVIE_RATING", movie_rating);
                                intent.putExtra("MOVIE_VOTES", movie_votes);
                                intent.putExtra("MOVIE_METASCORE", movie_metascore);
                                intent.putExtra("MOVIE_GROSS", movie_gross);
                                intent.putExtra("MOVIE_GENRE", movie_genre);
                                intent.putExtra("MOVIE_POSTER", movie_image);
                                intent.putExtra("MOVIE_STORY", movie_story);
                                startActivity(intent);
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else if(msg.arg2 == 300){

                    Toast.makeText(getApplicationContext(), "No internet connectivity", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MoviesPage.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };

    }
}