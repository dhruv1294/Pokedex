package com.example.pokedex;

import com.example.pokedex.models.Data;
import com.example.pokedex.models.PokemonDetails;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class PokeApi {
    public static final String url = "https://pokeapi.co/api/v2/";

    public static PokeService pokeService = null;
    public static PokeService getPokeService(){
        if(pokeService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            pokeService = retrofit.create(PokeService.class);
        }
        return pokeService;
    }
    public interface PokeService{
        @GET("pokemon/?limit=964&offset=0")
        Call<Data> getPokemonList();
    }
    public static PokeDetailService pokeDetailService = null;
    public static PokeDetailService getPokeDetailService(String baseUrl){
        if(pokeDetailService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            pokeDetailService = retrofit.create(PokeDetailService.class);
        }
        return pokeDetailService;
    }

    public interface PokeDetailService{
       @GET("/")
        Call<PokemonDetails> getPokemonDetails();
    }
}
