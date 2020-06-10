package com.example.pokedex;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pokedex.RoomDatabase.Poke;
import com.example.pokedex.RoomDatabase.PokeViewModel;
import com.example.pokedex.adapters.PokemonListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {
    public static PokeViewModel pokeViewModel;
    public static int fav =0;
    RecyclerView recyclerView;

    public FavouritesFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fav=1;
        final View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = view.findViewById(R.id.favouriteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         final PokemonListAdapter adapter = new PokemonListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        pokeViewModel =  ViewModelProviders.of(getActivity()).get(PokeViewModel.class);
        pokeViewModel.getAllPoke().observe(getActivity(), new Observer<List<Poke>>() {
            @Override
            public void onChanged(List<Poke> pokes) {
                adapter.setFavs(pokes);
                //Toast.makeText(getActivity(), "OnChanged", Toast.LENGTH_SHORT).show();
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                pokeViewModel.delete(adapter.removeFav(viewHolder.getAdapterPosition()));
                Snackbar.make(view,"Successfully Removed",Snackbar.LENGTH_SHORT).show();

                adapter.notifyDataSetChanged();

            }
        }).attachToRecyclerView(recyclerView);


        return view;
    }
    public void insert(Poke poke){

        pokeViewModel.insert(poke);
    }

}
