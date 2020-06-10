package com.example.pokedex.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PokeViewModel extends AndroidViewModel {
    private PokeRepository pokeRepository;
    private LiveData<List<Poke>> allPoke;
    public PokeViewModel(@NonNull Application application) {
        super(application);
        pokeRepository = new PokeRepository(application);
        allPoke = pokeRepository.getAllPoke();
    }

    public void insert(Poke poke){
        pokeRepository.insert(poke);

    }
    public  void delete(Poke poke){
        pokeRepository.delete(poke);
    }
    public LiveData<List<Poke>> getAllPoke(){
        return allPoke;
    }
}
