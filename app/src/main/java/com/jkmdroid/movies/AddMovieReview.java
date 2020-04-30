package com.jkmdroid.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
/**
 * this class accepts movie review and rating from SingleMovie
 * and posts it online
 * **/
public class AddMovieReview extends AppCompatActivity {
    String movie_review, movie_rating;
    static Handler handler = new Handler();
    ProgressDialog progressDialog;
    String movie_title;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_review);

        //change the title of the action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("MovieReviewer/Add Movie Review");

        //get the data from the SingleMovie activity
        Intent intent = getIntent();
        movie_title =  intent.getStringExtra("MOVIE_TITLE");
        final TextView m_title = findViewById(R.id.textview_movie_title);
        m_title.setText(movie_title);

        /*
          on clicking the submit button, take the review and rating
          pass it to the PostOnlineData class which will upload it to the
          database
          **/
        Button submit_review = findViewById(R.id.button_submit);
        submit_review.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onClick(View v) {

                final EditText edittext_review, edittext_rating;
                edittext_review = findViewById(R.id.edittext_review);
                edittext_rating = findViewById(R.id.edittext_rating);

                movie_review = edittext_review.getText().toString();
                movie_rating = edittext_rating.getText().toString();

                /*
                  perform the necessary validation to make sure the data is clean off errors
                  and to ensure the edit text are not empty before posting
                  **/
                if (movie_review.isEmpty()){

                    Toast.makeText(getApplicationContext(), "You cannot submit an empty review", Toast.LENGTH_SHORT).show();
                }

                if (movie_rating.equals("")){

                    Toast.makeText(getApplicationContext(), "You cannot submit an empty rating", Toast.LENGTH_SHORT).show();
                }


                if (!movie_review.isEmpty() && !movie_rating.equals("")){

                    float rating;

                    if (!edittext_rating.getText().toString().equals("")){

                        rating = Float.parseFloat(movie_rating);

                        if (rating >= 1 && rating < 10){
                            CheckNetworkStatus networkStatus = new CheckNetworkStatus();

                            //check if the device has internet connectivity
                            if (networkStatus.isNetworkAvailable(AddMovieReview.this)) {
                                /*
                                  show the progress dialog until the data is posted
                                  */
                                progressDialog = new ProgressDialog(AddMovieReview.this);
                                progressDialog.setMessage("Posting your review...Please wait");
                                progressDialog.setCancelable(false);
                                progressDialog.show();


                                /*
                                  start a new thread to handle the posting of the data
                                  **/
                                final Thread thread = new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();

                                        try {

                                            /*
                                              post the data using the PostOnlineData class
                                              **/
                                            String movie_url = "http://movieapi.mblog.co.ke/postdata.php";
                                            String response = PostOnlineData.uploadOnlineData(movie_url, movie_title, movie_review, movie_rating);

                                            /*
                                              send a message to the handler
                                              Use Bundle to send the response from the
                                              PostOnlineData class
                                              **/
                                            Message message;
                                            message = handler.obtainMessage();

                                            Bundle bundle = new Bundle();
                                            bundle.putString("RESPONSE", response);
                                            message.arg1 = 200;//message code for ok

                                            message.setData(bundle);
                                            handler.sendMessage(message);


                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                };
                                thread.start();
                                /*
                                  the handler receives the message from the thread and responds accordingly
                                  **/

                                handler = new Handler(){
                                    @SuppressLint("HandlerLeak")
                                    @Override
                                    public void handleMessage(@NonNull Message msg) {
                                        super.handleMessage(msg);

                                        if (msg.arg1 == 200){
                                            //dismiss the progress dialog
                                            progressDialog.dismiss();

                                          //get the response from the thread
                                            Bundle bundle = msg.getData();
                                            String response = bundle.getString("RESPONSE");

                                            try {
                                                //get the json object abd grab the message inside the object
                                                assert response != null;
                                                JSONObject jsonObject = new JSONObject(response);

                                                 String message = jsonObject.getString("message");

                                                 //compare if the strings are similar
                                                 if (message.equals("data saved successfully")){
                                                     //clear the edit texts after a successful submission
                                                     edittext_rating.setText("");
                                                     edittext_rating.setText("");
                                                     //display the success message
                                                     String s_msg = "Your post has been saved successfully";
                                                     makeToast(s_msg);
                                                 }else{
                                                     String s_msg = "An error occurred...Please try again";
                                                     makeToast(s_msg);
                                                 }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }else{
                                            //dismiss the progress dialog
                                            progressDialog.dismiss();

                                            //show the error message
                                            String message = "An error occurred...Please try again";
                                            setSnackBar(message);
                                        }
                                    }
                                };

                            }else{
                                //show an error message
                                String message = "Please enable your internet connection";
                                setSnackBar(message);
                            }
                        }else{
                            //show error message
                            String s_msg = "Your rating should be between 1 and 10";
                            makeToast(s_msg);
                        }

                    }else{
                        //show error message
                        String s_msg = "Rating is empty";
                        makeToast(s_msg);

                    }

                }
            }
        });

    }

    /**
     * method to create toasts and display to user
     * */
    private void makeToast(String message) {

        Toast.makeText(AddMovieReview.this, message, Toast.LENGTH_LONG).show();

    }

    /**
     * method to create snack bars and display to user
     * */
    private void setSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.add_movie_review_activity), message, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

}
