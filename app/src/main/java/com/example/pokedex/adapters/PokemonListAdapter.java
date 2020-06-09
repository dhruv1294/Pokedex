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
import com.example.pokedex.models.Data;
import com.example.pokedex.models.RegionPokemonList;
import com.example.pokedex.models.TypePokemonList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder> {

    private Context context;
    private List<Data.Pokemon> pokemonList;
    private TypePokemonList typePokemonList;
    private RegionPokemonList regionPokemonList;
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

        }else{
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
        }
    }

    @Override
    public int getItemCount() {
        if(typeList==0)
        return pokemonList.size();
        else if(typeList==1)
            return typePokemonList.getPokemon().size();
        else
            return regionPokemonList.getPokemonEntries().size();
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
