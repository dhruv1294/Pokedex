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
import com.example.pokedex.RegionDetailActivity;
import com.example.pokedex.models.Location;
import com.example.pokedex.models.Regions;

public class RegionListAdapter extends RecyclerView.Adapter<RegionListAdapter.RegionViewHolder> {

    Context context;
    Regions regions;
    Location locations;
    int locationList=0;

    public RegionListAdapter(Context context, Regions regions) {
        locationList=0;
        this.context = context;
        this.regions = regions;
    }
    public RegionListAdapter(Context context, Location locations) {
        locationList=1;
        this.context = context;
        this.locations = locations;
    }


    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.region_item,parent,false);
        return new RegionListAdapter.RegionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionViewHolder holder, final int position) {
        if(locationList==0) {
            holder.regionName.setText(regions.getResults().get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RegionDetailActivity.class);
                    intent.putExtra("regionName", regions.getResults().get(position).getName().toUpperCase());
                    intent.putExtra("regionId", getRegionId(position));
                    context.startActivity(intent);
                }
            });
        }else{
            holder.regionName.setText(locations.getResults().get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        if(locationList==0)
        return regions.getResults().size();
        else return locations.getResults().size();
    }

    public class RegionViewHolder extends RecyclerView.ViewHolder {
        TextView regionName;
        public RegionViewHolder(@NonNull View itemView) {
            super(itemView);
            regionName = itemView.findViewById(R.id.regionName);
        }
    }

    public int getRegionId(int position){
        switch (position) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 8;
            case 5:
                return 12;
            case 6:
                return 16;
                default:
                    return 1;

        }


    }
}
