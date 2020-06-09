package com.example.pokedex;

import com.example.pokedex.models.Data;
import com.example.pokedex.models.Item;
import com.example.pokedex.models.Location;
import com.example.pokedex.models.PokeType;
import com.example.pokedex.models.PokemonDetails;
import com.example.pokedex.models.RegionPokemonList;
import com.example.pokedex.models.Regions;
import com.example.pokedex.models.Type;
import com.example.pokedex.models.TypePokemonList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class PokeApi {
    public static final String url = "https://pokeapi.co/api/v2/";
    public static final String baseUrl= "https://pokeapi.co/api/v2/pokemon/";
    public static final String typeUrl = "https://pokeapi.co/api/v2/type/";
    public static final String regionUrl = "https://pokeapi.co/api/v2/";
    public static final String regionPokemonUrl = "https://pokeapi.co/api/v2/pokedex/";
    public static final String itemUrl = "https://pokeapi.co/api/v2/";



    public static PokeService pokeService = null;
    public static PokeService getPokeService(){
        if(pokeService==null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
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
    public static PokeDetailService getPokeDetailService(){
        if(pokeDetailService==null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            pokeDetailService = retrofit.create(PokeDetailService.class);
        }
        return pokeDetailService;
    }
    private static HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public interface PokeDetailService{
       @GET("{id}/")
        Call<PokemonDetails> getPokemonDetails(@Path("id") int id );
    }
    public static PokeTypeService pokeTypeService = null;
    public static PokeTypeService getPokeTypeService(){
        if(pokeTypeService==null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            pokeTypeService = retrofit.create(PokeTypeService.class);
        }
        return pokeTypeService;
    }
    public interface  PokeTypeService{
        @GET("type/")
        Call<PokeType> getPokemonTypes();
    }
    public static PokeTypeDetailService pokeTypeDetailService = null;
    public static PokeTypeDetailService getPokeTypeDetailService(){
        if(pokeTypeDetailService==null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(typeUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            pokeTypeDetailService = retrofit.create(PokeTypeDetailService.class);
        }
        return pokeTypeDetailService;
    }

    public interface PokeTypeDetailService{
        @GET("{id}/")
        Call<TypePokemonList> getTypeDetails(@Path("id") int id );

    }
    public static PokeRegionService pokeRegionService = null;
    public static PokeRegionService getPokeregionnService(){
        if(pokeRegionService==null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(regionUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            pokeRegionService = retrofit.create(PokeRegionService.class);
        }
        return pokeRegionService;
    }

    public interface PokeRegionService{
        @GET("region/")
        Call<Regions> getRegionList();
    }


    public static PokeRegionDetails pokeRegionDetailService = null;
    public static PokeRegionDetails getPokeRegionDetailService(){
        if(pokeRegionDetailService==null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(regionPokemonUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            pokeRegionDetailService = retrofit.create(PokeRegionDetails.class);
        }
        return pokeRegionDetailService;
    }

    public interface PokeRegionDetails{
        @GET("{id}/")
        Call<RegionPokemonList> getRegionDetails(@Path("id") int id );
    }

    public static ItemService itemService = null;
    public static ItemService getItemService(){
        if(itemService==null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(itemUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            itemService = retrofit.create(ItemService.class);
        }
        return itemService;
    }
    public interface ItemService{
        @GET("item/?limit=954&offset=0")
        Call<Item> getItemList();
    }
    public static LocationService locationService = null;
    public static LocationService getLocationService(){
        if(locationService==null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(getInterceptor()).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(regionUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            locationService = retrofit.create(LocationService.class);
        }
        return locationService;
    }

    public interface LocationService{
        @GET("location/?limit=781&offset=0")
        Call<Location> getLocationList();
    }
}
