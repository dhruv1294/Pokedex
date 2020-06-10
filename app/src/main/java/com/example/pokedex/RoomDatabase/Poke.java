package com.example.pokedex.RoomDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "poke_table")
public class Poke {
    private String name;
    private String id;
    private String imageUrl;

    @PrimaryKey(autoGenerate = true)
    private int idsr;

    public Poke(String name, String id, String imageUrl) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public int getIdsr() {
        return idsr;
    }

    public void setIdsr(int idsr) {
        this.idsr = idsr;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
