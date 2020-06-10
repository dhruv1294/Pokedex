package com.example.pokedex.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.MainActivity;
import com.example.pokedex.PokeDetailsActivity;
import com.example.pokedex.R;
import com.example.pokedex.RoomDatabase.Poke;
import com.example.pokedex.models.Data;
import com.example.pokedex.models.RegionPokemonList;
import com.example.pokedex.models.TypePokemonList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder> {

    private Context context;
    private List<Data.Pokemon> pokemonList;
    private TypePokemonList typePokemonList;
    private RegionPokemonList regionPokemonList;
    public List<Poke> favPokemons = new ArrayList<>();
    Activity a;
    int typeList=0;
    public PokemonListAdapter(Context ctx,Activity a,List<Data.Pokemon> pokemons){
        typeList=0;
        this.a =a;
        context = ctx;
        pokemonList = pokemons;
    }
    public PokemonListAdapter(Context ctx,Activity a,TypePokemonList typePokemonList){
        typeList=1;
        this.a =a;
        context = ctx;
        this.typePokemonList = typePokemonList;
    }
    public PokemonListAdapter(Context ctx,Activity a,RegionPokemonList regionPokemonList){
        typeList=2;
        this.a =a;
        context = ctx;
        this.regionPokemonList = regionPokemonList;
    }
    public PokemonListAdapter(Context ctx){
        typeList=3;
        context = ctx;
    }




    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pokemon_item,parent,false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PokemonViewHolder holder, final int position) {
        if(typeList==0) {
            final Data.Pokemon pokemon = pokemonList.get(position);
            final String url = pokemonList.get(position).getUrl();
            String result = null;
            if(url.length()>0){
                result = url.substring(34,url.length()-1);
            }
            holder.pokemonId.setText("#" + result);
            holder.pokemonName.setText(pokemon.getName());
            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + (result) + ".png").into(holder.pokemonImage);
            final String finalResult = result;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PokeDetailsActivity.class);
                    intent.putExtra("url", pokemon.getUrl());
                    intent.putExtra("position", Integer.parseInt(finalResult));
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(a,v.findViewById(R.id.pokemonImage),"pokeImage");
                    context.startActivity(intent,optionsCompat.toBundle());

                }
            });
        }else if(typeList==1){
            final String url = typePokemonList.getPokemon().get(position).getPokemon().getUrl();
            String result = null;
            if(url.length()>0){
                result = url.substring(34,url.length()-1);
            }
            holder.pokemonName.setText(typePokemonList.getPokemon().get(position).getPokemon().getName());
            holder.pokemonId.setText("#" + result);
            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + result + ".png").into(holder.pokemonImage);
            final String finalResult = result;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent intent = new Intent(context, PokeDetailsActivity.class);
                        intent.putExtra("url", url);
                        intent.putExtra("position", Integer.parseInt(finalResult));
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(a,v.findViewById(R.id.pokemonImage),"pokeImage");

                        context.startActivity(intent,optionsCompat.toBundle());


                }
            });

        }else if(typeList==2){
            final String urlId = regionPokemonList.getPokemonEntries().get(position).getPokemonSpecies().getUrl();
            String result = null;
            if(urlId.length()>0){
                result = urlId.substring(42,urlId.length()-1);
            }
            holder.pokemonName.setText(regionPokemonList.getPokemonEntries().get(position).getPokemonSpecies().getName());
            holder.pokemonId.setText("#"+result);
            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + result + ".png").into(holder.pokemonImage);
            final String finalResult = result;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,PokeDetailsActivity.class);
                    intent.putExtra("url",regionPokemonList.getPokemonEntries().get(position).getPokemonSpecies().getUrl());
                    intent.putExtra("position", Integer.parseInt(finalResult));
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(a,v.findViewById(R.id.pokemonImage),"pokeImage");
                    context.startActivity(intent,optionsCompat.toBundle());
                }
            });
        }else{
            holder.pokemonName.setText(favPokemons.get(position).getName());
            holder.pokemonId.setText("#"+favPokemons.get(position).getId());
            Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + favPokemons.get(position).getId() + ".png").into(holder.pokemonImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PokeDetailsActivity.class);
                    intent.putExtra("url", favPokemons.get(position).getImageUrl());
                    intent.putExtra("position", Integer.parseInt(favPokemons.get(position).getId()));
                   // ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(a,v.findViewById(R.id.pokemonImage),"pokeImage");
                    context.startActivity(intent);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,"My Favourite Pokemon is " + favPokemons.get(position).getName().toUpperCase()+ " and image url is " + favPokemons.get(position).getImageUrl());
                    context.startActivity(Intent.createChooser(intent,"Share Using"));
                    return false;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if(typeList==0)
        return pokemonList.size();
        else if(typeList==1)
            return typePokemonList.getPokemon().size();
        else if(typeList==2)
            return regionPokemonList.getPokemonEntries().size();
        else
            return favPokemons.size();
    }
    public void setFavs(List<Poke> pokes){
        favPokemons = pokes;
        notifyDataSetChanged();
    }
    public Poke getFav(int position){
        String name = pokemonList.get(position).getName();
        String url = pokemonList.get(position).getUrl();
        String result = null;
        if(url.length()>0){
            result = url.substring(34,url.length()-1);
        }
        String id = result;
        String imageUrl =  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + result + ".png";
        return new Poke(name,id,imageUrl);
    }
    public Poke removeFav(int position){
        Poke poke = favPokemons.get(position);
        favPokemons.remove(position);
        notifyDataSetChanged();
       return poke;
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder{
        ImageView pokemonImage;
        TextView pokemonId;
        TextView pokemonName;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.pokemonImage);
            pokemonId = itemView.findViewById(R.id.pokemonId);
            pokemonName = itemView.findViewById(R.id.pokemonName);
        }
    }

    public void filterList(List<Data.Pokemon> pokemonList){
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }
}
