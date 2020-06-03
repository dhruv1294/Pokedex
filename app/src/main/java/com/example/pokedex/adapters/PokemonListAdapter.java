package com.example.pokedex.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.PokeDetailsActivity;
import com.example.pokedex.R;
import com.example.pokedex.models.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder> {

    private Context context;
    private List<Data.Pokemon> pokemonList;
    public PokemonListAdapter(Context ctx,List<Data.Pokemon> pokemons){
        context = ctx;
        pokemonList = pokemons;
    }
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pokemon_item,parent,false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
      final Data.Pokemon pokemon = pokemonList.get(position);
      holder.pokemonId.setText("#"+Integer.toString(position+1));
      holder.pokemonName.setText(pokemon.getName());
        Picasso.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(position+1)+".png").into(holder.pokemonImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PokeDetailsActivity.class);
                /*String url = pokemon.getUrl();
                String result = null;
                if((url!=null)&&(url.length()>0)){
                    result = url.substring(0,url.length()-1);
                }*/
                intent.putExtra("url",pokemon.getUrl());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
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
}
