package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pokedex.models.PokemonDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_details);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.i("url",url);
        getData(url);

    }
    public void getData(String url){
        final Call<PokemonDetails> pokeDetails = PokeApi.getPokeDetailService(url).getPokemonDetails();
        pokeDetails.enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                PokemonDetails pokemonDetails = response.body();
                int height = pokemonDetails.getHeight();
                Log.i("height","done");
                Toast.makeText(PokeDetailsActivity.this, "Success"+(height), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {
                Toast.makeText(PokeDetailsActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
