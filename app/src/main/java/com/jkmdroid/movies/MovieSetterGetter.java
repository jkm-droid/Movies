package com.jkmdroid.movies;

/**
 * this class sets the different elements of
 * a movie and also creates getter functions for those elements
 * **/
public class MovieSetterGetter {

    private String title, year;
    private String  duration;
    private String movie_poster;
    private String votes, story, genre;
    private String rating, gross, metascore;

    public void setMovie_poster(String movie_poster) {
        this.movie_poster = movie_poster;
    }

    /**
     * the setters for storing the specific element values
     * **/

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStory(String story) {
        this.story = story;
    }


    /**
     * the getters for getting the stored values
     * **/

    public String getMovie_poster() {
        return movie_poster;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDuration() {
        return duration;
    }

    public String getVotes() {
        return votes;
    }

    public String getRating() {
        return rating;
    }

    public String getGross() {
        return gross;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getGenre() {
        return genre;
    }

    public String getStory() {
        return story;
    }
}