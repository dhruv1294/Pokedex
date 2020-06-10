package com.example.pokedex;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pokedex.RoomDatabase.Poke;
import com.example.pokedex.RoomDatabase.PokeViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {
    private PokeViewModel pokeViewModel;

    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        pokeViewModel = new ViewModelProvider(this).get(PokeViewModel.class);
        pokeViewModel.getAllPoke().observe(requireActivity(), new Observer<List<Poke>>() {
            @Override
            public void onChanged(List<Poke> pokes) {
                Toast.makeText(getActivity(), "OnChanged", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
