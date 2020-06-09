
package com.example.pokedex.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionPokemonList {

    @SerializedName("pokemon_entries")
    @Expose
    private List<PokemonEntry> pokemonEntries = null;

    public List<PokemonEntry> getPokemonEntries() {
        return pokemonEntries;
    }

    public void setPokemonEntries(List<PokemonEntry> pokemonEntries) {
        this.pokemonEntries = pokemonEntries;
    }

}
