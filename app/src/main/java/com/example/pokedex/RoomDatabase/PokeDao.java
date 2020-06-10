package com.example.pokedex.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PokeDao {
    @Insert
    void insert(Poke poke);
    @Delete
    void delete(Poke poke);
    @Query("SELECT * FROM poke_table")
    LiveData<List<Poke>> getAllPokes();


}
