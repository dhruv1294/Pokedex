package com.example.pokedex.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PokeRepository {
    private PokeDao pokeDao;
    private LiveData<List<Poke>> allPoke;

    public PokeRepository(Application application){
        PokeDatabase pokeDatabase = PokeDatabase.getInstance(application);
        pokeDao = pokeDatabase.pokeDao();
        allPoke = pokeDao.getAllPokes();
    }

    public void insert(Poke poke){
     new InsertPokeAsyncTask(pokeDao).execute(poke);
    }
    public  void delete(Poke poke){
    new DeletePokeAsyncTask(pokeDao).execute(poke);
    }

    public LiveData<List<Poke>> getAllPoke(){
        return allPoke;
    }

    private static class InsertPokeAsyncTask extends AsyncTask<Poke,Void,Void>{
        private PokeDao pokeDao;
        private InsertPokeAsyncTask(PokeDao pokeDao){
            this.pokeDao = pokeDao;
        }
        @Override
        protected Void doInBackground(Poke... pokes) {
            pokeDao.insert(pokes[0]);
            return null;
        }
    }

    private static class DeletePokeAsyncTask extends AsyncTask<Poke,Void,Void>{
        private PokeDao pokeDao;
        private DeletePokeAsyncTask(PokeDao pokeDao){
            this.pokeDao = pokeDao;
        }
        @Override
        protected Void doInBackground(Poke... pokes) {
            pokeDao.delete(pokes[0]);
            return null;
        }
    }


}
