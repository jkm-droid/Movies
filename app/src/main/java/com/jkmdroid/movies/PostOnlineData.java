package com.jkmdroid.movies;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * this class accepts data from the AddMovieReview class and sends the data to
 * the online database, gets the response and returns it
 * **/
public class PostOnlineData {

     static String uploadOnlineData(String movie_url, String movie_review, String movie_rating) throws IOException{

        /*
          open connection to the server,post the data to the
          server, and get the response
          return the response from the server
          **/
        HttpURLConnection httpURLConnection;
        URL url;
        url = new URL(movie_url);
        httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);

        String charset = "UTF-8";

        OutputStream outputStream = httpURLConnection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        String post_data = URLEncoder.encode("movie_review", charset)+"="+URLEncoder.encode(movie_review, charset)+"&"+
                URLEncoder.encode("movie_rating", charset)+"="+URLEncoder.encode(movie_rating, charset);

        bufferedWriter.write(post_data);

        bufferedWriter.flush();
        bufferedWriter.close();

        /*
          getting the response from the server after sending the data
          **/
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }

        /*
          close all the connections and return the data
          **/
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();

        return stringBuilder.toString();
    }
}
