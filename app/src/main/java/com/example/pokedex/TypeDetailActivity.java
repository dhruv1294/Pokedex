package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.example.pokedex.adapters.PokemonListAdapter;
import com.example.pokedex.models.Data;
import com.example.pokedex.models.TypePokemonList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    int id;
    AllianceLoader allianceLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_detail);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allianceLoader = findViewById(R.id.progressLoader);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("typename"));
        setSupportActionBar(toolbar);
        id = getIntent().getIntExtra("id",1);
        getData();

    }
    public void getData(){
        Call<TypePokemonList> data= PokeApi.getPokeTypeDetailService().getTypeDetails(id);
        data.enqueue(new Callback<TypePokemonList>() {
            @Override
            public void onResponse(Call<TypePokemonList> call, Response<TypePokemonList> response) {
                TypePokemonList pokemon = response.body();
                allianceLoader.setVisibility(View.GONE);
                recyclerView.setAdapter(new PokemonListAdapter(TypeDetailActivity.this,TypeDetailActivity.this,pokemon));

                //Toast.makeText(TypeDetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TypePokemonList> call, Throwable t) {
                //Toast.makeText(TypeDetailActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
