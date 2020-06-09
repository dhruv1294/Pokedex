package com.example.pokedex;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.example.pokedex.adapters.ItemListAdapter;
import com.example.pokedex.adapters.TypeListAdapter;
import com.example.pokedex.models.Item;
import com.example.pokedex.models.PokeType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment {

    RecyclerView recyclerView;
    AllianceLoader allianceLoader;
    public ItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        recyclerView = view.findViewById(R.id.itemList);
        allianceLoader = view.findViewById(R.id.progressLoader);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return view;
    }

    public void getData(){
        Call<Item> item = PokeApi.getItemService().getItemList();
        item.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item pokemon = response.body();
                allianceLoader.setVisibility(View.GONE);

                recyclerView.setAdapter(new ItemListAdapter(getActivity(),pokemon.getResults()));

                // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                // Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
