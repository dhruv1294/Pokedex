package com.example.pokedex;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.example.pokedex.RoomDatabase.Poke;
import com.example.pokedex.RoomDatabase.PokeViewModel;
import com.example.pokedex.adapters.PokemonListAdapter;
import com.example.pokedex.models.Data;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonListFragment extends Fragment {

RecyclerView recyclerView;
AllianceLoader allianceLoader;
EditText searchText;
    Data pokemon;
    PokemonListAdapter pokemonListAdapter;

    PokeViewModel pokeViewModel;

    public PokemonListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        allianceLoader = view.findViewById(R.id.progressLoader);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchText = view.findViewById(R.id.search_text);

        pokeViewModel = ViewModelProviders.of(this).get(PokeViewModel.class);
        getData();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
             filter(s.toString());
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Poke poke = pokemonListAdapter.getFav(viewHolder.getAdapterPosition());
                String pokeName = poke.getName();
                String id = poke.getId();
                String imageUrl = poke.getImageUrl();

              pokeViewModel.insert(poke);
                Snackbar.make(view,"Successfully added to favourites",Snackbar.LENGTH_SHORT).show();


            }
        }).attachToRecyclerView(recyclerView);
        return view;

    }

    public void getData(){
        Call<Data> data= PokeApi.getPokeService().getPokemonList();
        data.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                pokemon = response.body();
                allianceLoader.setVisibility(View.GONE);
                pokemonListAdapter = new PokemonListAdapter(getActivity(),getActivity(),pokemon.getResults());
                recyclerView.setAdapter(pokemonListAdapter);

                //Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
               // Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void filter(String text){
        ArrayList<Data.Pokemon> filteredList = new ArrayList<>();
        for(Data.Pokemon item : pokemon.getResults()){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        pokemonListAdapter.filterList(filteredList);
    }

}
