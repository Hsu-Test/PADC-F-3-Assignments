package xyz.hsuyeemon.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import xyz.hsuyeemon.movies.R;
import xyz.hsuyeemon.movies.viewholders.ItemMoviesViewHolder;

/**
 * Created by Dell on 12/8/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View movieViewHolder=inflater.inflate(R.layout.item_movies,parent,false);
        ItemMoviesViewHolder itemMoviesViewHolder=new ItemMoviesViewHolder(movieViewHolder);
        return itemMoviesViewHolder;

        }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
