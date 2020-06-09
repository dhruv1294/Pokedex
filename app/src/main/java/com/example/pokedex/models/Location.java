package com.example.pokedex.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {
    @SerializedName("results")
    private List<LocationList> results = null;
    public List<LocationList> getResults() {
        return results;
    }
    public class LocationList{
        @SerializedName("name")
        private String name;
        public String getName() {
            return name;
        }
    }
}
