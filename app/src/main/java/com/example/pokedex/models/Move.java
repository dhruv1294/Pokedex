package com.example.pokedex.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Move {
    @SerializedName("move")
    @Expose
    private Move_ move;
    public Move_ getMove() {
        return move;
    }
}
