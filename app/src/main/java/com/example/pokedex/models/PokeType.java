package com.example.pokedex.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokeType {
    @SerializedName("results")
    private List<PokemonType> results = null;

    public List<PokemonType> getResults() {
        return results;
    }


    public class PokemonType{
        @SerializedName("url")
        private String url;
        @SerializedName("name")
        private String name;
        public String getName() {
            return name;
        }
    }
}
