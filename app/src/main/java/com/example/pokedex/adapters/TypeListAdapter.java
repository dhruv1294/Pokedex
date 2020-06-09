package com.example.pokedex.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.TypeDetailActivity;
import com.example.pokedex.models.PokeType;
import com.robertlevonyan.views.chip.Chip;

import java.util.List;

public class TypeListAdapter extends RecyclerView.Adapter<TypeListAdapter.TypeViewHolder> {
    Context context;
    List<PokeType.PokemonType> types;

    public TypeListAdapter(Context context, List<PokeType.PokemonType> types) {
        this.context = context;
        this.types = types;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.type_item,parent,false);
        return new TypeListAdapter.TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, final int position) {
        final String name = types.get(position).getName().toUpperCase();
        holder.type.setChipText(types.get(position).getName().toUpperCase());
        int color = DetailTypeAdapter.getColorByType(types.get(position).getName());
        holder.type.changeBackgroundColor(color);
        holder.chipBg.setBackgroundColor(color);
        holder.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TypeDetailActivity.class);
                intent.putExtra("typename",name);
                intent.putExtra("id",position+1);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return types.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {
       Chip type;
       TextView chipBg;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.typeListItem);
            chipBg = itemView.findViewById(R.id.chipBg);
        }
    }

}
