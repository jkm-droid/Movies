package com.jkmdroid.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<MovieSetterGetter> {

    private Context mcontext;
    private ArrayList<MovieSetterGetter> moviesList = new ArrayList<>();


    public MovieAdapter(@NonNull Context context, ArrayList<MovieSetterGetter> arrayList) {
        super(context, 0, arrayList);

        mcontext =context;
        moviesList = arrayList;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        MovieSetterGetter movieSetterGetter = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movies_row, parent, false);

        }

        //MovieSetterGetter currentMovie = moviesList.get(position);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(movieSetterGetter.getMovie_poster());

        TextView movie_name = convertView.findViewById(R.id.movie_name);
        movie_name.setText(movieSetterGetter.getTitle());

        return convertView;
    }
}
