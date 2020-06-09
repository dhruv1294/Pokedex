package com.example.pokedex;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.example.pokedex.adapters.PokemonListAdapter;
import com.example.pokedex.adapters.TypeListAdapter;
import com.example.pokedex.models.Data;
import com.example.pokedex.models.PokeType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeListFragment extends Fragment {


    RecyclerView recyclerView;
    AllianceLoader allianceLoader;
    public TypeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_list, container, false);
        recyclerView = view.findViewById(R.id.typeList);
        allianceLoader = view.findViewById(R.id.progressLoader);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return view;
    }
    public void getData(){
        Call<PokeType> type= PokeApi.getPokeTypeService().getPokemonTypes();
        type.enqueue(new Callback<PokeType>() {
            @Override
            public void onResponse(Call<PokeType> call, Response<PokeType> response) {
                PokeType pokemon = response.body();
                allianceLoader.setVisibility(View.GONE);
                pokemon.getResults().remove(pokemon.getResults().size()-1);
                pokemon.getResults().remove(pokemon.getResults().size()-1);
                recyclerView.setAdapter(new TypeListAdapter(getActivity(),pokemon.getResults()));

               // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PokeType> call, Throwable t) {
               // Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
