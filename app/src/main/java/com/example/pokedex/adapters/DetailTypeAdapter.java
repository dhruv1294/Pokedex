package com.example.pokedex.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.models.Type;
import com.robertlevonyan.views.chip.Chip;

import java.util.List;

public class DetailTypeAdapter extends RecyclerView.Adapter<DetailTypeAdapter.MyViewHolder> {
    Context context;
    List<Type> pokeTypes;

    public DetailTypeAdapter(Context context, List<Type> pokeTypes) {
        this.context = context;
        this.pokeTypes = pokeTypes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chip_item,parent,false);
        return new DetailTypeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     holder.pokeType.setChipText(pokeTypes.get(position).getType().getName().toUpperCase());
     int color = getColorByType(pokeTypes.get(position).getType().getName());

    holder.pokeType.changeBackgroundColor(color);


    }

    @Override
    public int getItemCount() {
        return pokeTypes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Chip pokeType;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pokeType = itemView.findViewById(R.id.pokeType);
        }
    }
    public static int getColorByType(String type) {
        switch(type)
        {

            case "normal":
                return Color.parseColor("#A4A27A");


            case "dragon":
                return Color.parseColor("#743BFB");



            case "psychic":
                return Color.parseColor("#F15B85");


            case "electric":
                return Color.parseColor("#E9CA3C");


            case "ground":
                return Color.parseColor("#D9BF6C");


            case "grass":
                return Color.parseColor("#81C85B");

            case "poison":
                return Color.parseColor("#A441A3");

            case "steel":
                return Color.parseColor("#BAB7D2");


            case "fairy":
                return Color.parseColor("#DDA2DF");


            case "fire":
                return Color.parseColor("#F48130");


            case "fight":
                return Color.parseColor("#BE3027");


            case "bug":
                return Color.parseColor("#A8B822");


            case "ghost":
                return Color.parseColor("#705693");


            case "dark":
                return Color.parseColor("#745945");


            case "ice":
                return Color.parseColor("#9BD8D8");


            case "water":
                return Color.parseColor("#658FF1");
            default:
                return Color.parseColor("#658FA0");
        }
    }
}
