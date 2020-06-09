package com.example.pokedex;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.example.pokedex.adapters.RegionListAdapter;
import com.example.pokedex.adapters.TypeListAdapter;
import com.example.pokedex.models.PokeType;
import com.example.pokedex.models.Regions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegionListFragment extends Fragment {

    RecyclerView recyclerView;
    AllianceLoader allianceLoader;
    public RegionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_region_list, container, false);
        allianceLoader = view.findViewById(R.id.progressLoader);
        recyclerView = view.findViewById(R.id.regionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        return view;
    }
    public void getData(){
        Call<Regions> region= PokeApi.getPokeregionnService().getRegionList();
        region.enqueue(new Callback<Regions>() {
            @Override
            public void onResponse(Call<Regions> call, Response<Regions> response) {
                Regions region = response.body();
                allianceLoader.setVisibility(View.GONE);
                recyclerView.setAdapter(new RegionListAdapter(getActivity(),region));

                // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Regions> call, Throwable t) {
                // Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
