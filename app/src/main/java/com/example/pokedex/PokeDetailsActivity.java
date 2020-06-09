package com.example.pokedex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
import com.example.pokedex.adapters.DetailAbilityAdapter;
import com.example.pokedex.adapters.DetailMoveAdapter;
import com.example.pokedex.adapters.DetailStatAdapter;
import com.example.pokedex.adapters.DetailTypeAdapter;
import com.example.pokedex.models.PokemonDetails;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class PokeDetailsActivity extends AppCompatActivity {
    TextView pokeName,pokeHeight,pokeWeight,pokeExperience;
    RecyclerView recyclerType,recyclerStats,recyclerAbilities,recyclerMoves;
    ImageView pokeImage;
    PokemonDetails pokemonDetails;
    public static Integer id;
    public static int geturl;
    RelativeLayout bgLayout;
    String result;
    AllianceLoader allianceLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_details);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        allianceLoader = findViewById(R.id.progressLoader);
        bgLayout = findViewById(R.id.bgLayout);
        bgLayout.setVisibility(View.INVISIBLE);

        result = null;
        if(url.length()>0){
            result = url.substring(34,url.length()-1);
        }

        id = intent.getIntExtra("position",1);
        geturl = id;
        getData();
        pokeName = findViewById(R.id.pokeDetailName);
        pokeHeight = findViewById(R.id.pokeHeight);
        pokeWeight = findViewById(R.id.pokeWeight);
        pokeExperience = findViewById(R.id.pokeExperience);
        pokeImage = findViewById(R.id.pokeImage);

        recyclerType = findViewById(R.id.recyclerType);
        recyclerType.setLayoutManager(new LinearLayoutManager(PokeDetailsActivity.this,RecyclerView.HORIZONTAL,false));
        recyclerAbilities =findViewById(R.id.recyclerAbilities);
        recyclerAbilities.setLayoutManager(new LinearLayoutManager(PokeDetailsActivity.this,LinearLayoutManager.VERTICAL,false));
        recyclerStats = findViewById(R.id.recyclerStats);
        recyclerStats.setLayoutManager(new LinearLayoutManager(PokeDetailsActivity.this));
        recyclerMoves = findViewById(R.id.recyclerMoves);
        recyclerMoves.setLayoutManager(new LinearLayoutManager(PokeDetailsActivity.this));



    }
    public void getData(){
        Call<PokemonDetails> pokeDetails = PokeApi.getPokeDetailService().getPokemonDetails(geturl);
        pokeDetails.enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                pokemonDetails = response.body();

                allianceLoader.setVisibility(View.GONE);
                bgLayout.setVisibility(View.VISIBLE);
                setView();

               // Toast.makeText(PokeDetailsActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {
               // Toast.makeText(PokeDetailsActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setView(){
        float height;
        float weight;
       /* if(pokemonDetails.getHeight()==null ){
            height=0;

        }else*/{
            height = (float)pokemonDetails.getHeight()/10;
        }
       /* if( pokemonDetails.getWeight()==null){
            weight=0;
        }else*/{
            weight =  (float)pokemonDetails.getWeight()/10;
        }
        pokeName.setText(pokemonDetails.getName());
        pokeHeight.setText("Height: "+ height + "m");
        pokeWeight.setText("Weight: "+ weight + "Kg");
        pokeExperience.setText("Base Experience: "+ pokemonDetails.getBaseExperience() + "xp");
        Picasso.with(PokeDetailsActivity.this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+geturl+".png").into(pokeImage);
        recyclerType.setAdapter(new DetailTypeAdapter(PokeDetailsActivity.this,pokemonDetails.getTypes()));
        recyclerAbilities.setAdapter(new DetailAbilityAdapter(PokeDetailsActivity.this,pokemonDetails.getAbilities()));
        recyclerStats.setAdapter(new DetailStatAdapter(PokeDetailsActivity.this,pokemonDetails.getStats()));
        recyclerMoves.setAdapter(new DetailMoveAdapter(PokeDetailsActivity.this,pokemonDetails.getMoves()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
