package com.jkmdroid.movies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * this class handles all the logic on downloading json data
 * **/

public class GetOnlineData {

    /**
     * this method takes a link as an argument, downloads the required data and
     * returns the data in string format
     * **/
    static String downloadMovies(String movies_url) throws IOException{

        HttpURLConnection connection = null;

        URL url;

        try {
            url = new URL(movies_url);
            connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(1000);
            connection.setConnectTimeout(600);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        InputStream inputStream;
        BufferedReader br;

        /**
         * check whether the server response code is correct
         * then get the data from the server
         * return the data as a string
         * **/
        int response_code = connection.getResponseCode();

        if (response_code == HttpURLConnection.HTTP_OK){

            inputStream = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while((line = br.readLine()) != null){

                stringBuilder.append(line);

            }

            /**
             * close all the connections after getting the data
             * */
            br.close();
            inputStream.close();
            connection.disconnect();

            return stringBuilder.toString();
        }else {
            throw new IOException("Server error"+response_code);
        }

    }


}
