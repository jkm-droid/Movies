package com.jkmdroid.movies;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * this class handles the main logic such as getting movies json and displaying it to the user
 * **/

public class MoviesPage extends AppCompatActivity {
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        /**
         * thread for handling the heavy task of getting movies from server
         * **/
        final Thread thread = new Thread(){

            @Override
            public void run() {
                super.run();

                try {

                    /**
                     * download movies from the link suing the GetOnlineDate class
                     * and one of its method
                     * **/
                    String movies_url = "http://movieapi.mblog.co.ke/getdata.php";
                    String response = GetOnlineData.downloadMovies(movies_url);

                    Message msg;
                    msg = handler.obtainMessage();

                    /**
                     * prepare the data and send it to the handle
                     * for display in the listview
                     * ***/
                    Bundle bundle = new Bundle();
                    bundle.putString("RESPONSE", response);
                    msg.arg1 = 200;
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
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
                    Bundle bundle = msg.getData();
                    String response = bundle.getString("RESPONSE");

                    try {
                        /**
                         * get the json object from the link, get the json array inside
                         * the json object
                         * loop through the specific array elements to get them
                         * e.g title, year etc
                         * **/
                        JSONObject moviesObject = new JSONObject(response);
                        JSONArray moviesArray = moviesObject.getJSONArray("movies");

                        int length = moviesArray.length();
                        String[] titles = new String[moviesArray.length()];

                        ArrayList<MovieSetterGetter> arrayListMovies = new ArrayList<>();

                        MovieSetterGetter movieSetterGetter;

                        for(int i = 0; i < length; i++){

                            JSONObject jsonObject = moviesArray.getJSONObject(i);

                            movieSetterGetter = new MovieSetterGetter();
                            movieSetterGetter.setTitle(jsonObject.getString("title"));
                            //movieSetterGetter.setYear(jsonObject.getInt("duration"));

                            titles[i] = jsonObject.getString("title");

                            arrayListMovies.add(movieSetterGetter);

                        }

                        /**
                         * populate the data into the listview and render it to
                         * the user
                         * **/
                        ListView listView = findViewById(R.id.listview_movies);
                        //MovieAdapter arrayAdapter = new MovieAdapter(getApplicationContext(), arrayListMovies );
                        //ArrayAdapter<MovieSetterGetter> arrayAdapter = new ArrayAdapter<MovieSetterGetter>(MoviesPage.this,android.R.layout.simple_list_item_1, arrayListMovies);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, titles);
                        listView.setAdapter(arrayAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

    }
}
