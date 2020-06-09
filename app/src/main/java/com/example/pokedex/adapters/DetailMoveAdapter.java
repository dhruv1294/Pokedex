package com.example.pokedex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.R;
import com.example.pokedex.models.Move;
import com.example.pokedex.models.Regions;

import java.util.List;

public class DetailMoveAdapter extends RecyclerView.Adapter<DetailMoveAdapter.MoveViewHolder> {
    Context context;
    List<Move> Moves;


    public DetailMoveAdapter(Context context, List<Move> moves) {
        this.context = context;
        Moves = moves;
    }



    @NonNull
    @Override
    public MoveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.move_item,parent,false);
        return new DetailMoveAdapter.MoveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveViewHolder holder, int position) {
        holder.pokeMove.setText(Moves.get(position).getMove().getName());
    }

    @Override
    public int getItemCount() {
        return Moves.size();
    }

    public class MoveViewHolder extends RecyclerView.ViewHolder {
        TextView pokeMove;
        public MoveViewHolder(@NonNull View itemView) {
            super(itemView);
            pokeMove = itemView.findViewById(R.id.pokeMove);
        }
    }
}
