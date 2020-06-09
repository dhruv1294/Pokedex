package com.example.pokedex.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @SerializedName("results")
    private List<ItemList> results = null;
    public List<ItemList> getResults() {
        return results;
    }
    public class ItemList{
        @SerializedName("name")
        private String name;
        public String getName() {
            return name;
        }
    }

}
