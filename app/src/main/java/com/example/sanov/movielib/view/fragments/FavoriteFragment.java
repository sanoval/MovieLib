package com.example.sanov.movielib.view.fragments;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanov.movielib.R;
import com.example.sanov.movielib.adapter.FavoriteAdapter;

import static com.example.sanov.movielib.db.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    private Cursor list;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.rv_movie_now_playing_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        favoriteAdapter = new FavoriteAdapter(getActivity());
        favoriteAdapter.setListFavorites(list);
        recyclerView.setAdapter(favoriteAdapter);

        new LoadListAsync().execute();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private class LoadListAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            list = cursor;
            favoriteAdapter.setListFavorites(list);
            favoriteAdapter.notifyDataSetChanged();

            if (list.getCount() == 0) {
                Snackbar.make(recyclerView, getResources().getString(R.string.no_data), Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
