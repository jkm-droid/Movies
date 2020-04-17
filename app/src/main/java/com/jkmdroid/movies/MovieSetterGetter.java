package com.jkmdroid.movies;

/**
 * this class sets the different elements of
 * a movie and also creates getter functions for those elements
 * **/
public class MovieSetterGetter {

    private String title;
    private int year, duration, movie_poster;
    private long votes;
    private double rating, gross, metascore;

    public void setMovie_poster(int movie_poster) {
        this.movie_poster = movie_poster;
    }

    /**
     * the setters for storing the specific element values
     * **/

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    public void setMetascore(double metascore) {
        this.metascore = metascore;
    }


    public int getMovie_poster() {
        return movie_poster;
    }

    /**
     * the getters for getting the stored values
     * **/

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public long getVotes() {
        return votes;
    }

    public double getRating() {
        return rating;
    }

    public double getGross() {
        return gross;
    }

    public double getMetascore() {
        return metascore;
    }
}
