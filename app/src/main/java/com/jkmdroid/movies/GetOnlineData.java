package com.jkmdroid.movies;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/**
 * this class handles all the logic on downloading json data
 * **/

public class GetOnlineData {

    /**
     * this method takes a link as an argument, downloads the required data and
     * returns the data in string format
     * **/
    static String downloadMovies(String movies_url, String data) throws IOException{

        HttpURLConnection connection = null;

        URL url;

        try {
            url = new URL(movies_url);
            connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(25000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);


            /**
             * writing data to the server
             * **/
            OutputStreamWriter outputStreamWriter;
            BufferedWriter bufferedWriter;
            String charset  = "UTF-8";

            outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            String post_data = URLEncoder.encode("data", charset) + "=" + URLEncoder.encode(data, charset);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();


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
        assert connection != null;
        int response_code = connection.getResponseCode();

        //if (response_code == HttpURLConnection.HTTP_OK){

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
        //}else {
            //throw new IOException("Server error"+response_code);
       // }

    }


}
