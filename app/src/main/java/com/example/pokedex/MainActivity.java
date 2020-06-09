package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pokedex.adapters.PokemonListAdapter;
import com.example.pokedex.models.Data;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PokemonListFragment()).commit();
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.pokemons:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PokemonListFragment()).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.typeDex:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TypeListFragment()).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.regionDex:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegionListFragment()).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.itemDex:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ItemListFragment()).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.locationDex:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LocationListFragment()).commit();
                        drawerLayout.closeDrawers();
                        break;


                }
                return true;
            }
        });

    }

    private void setUpToolBar(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }
}
