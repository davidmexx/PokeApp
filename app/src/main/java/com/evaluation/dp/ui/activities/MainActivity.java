package com.evaluation.dp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.evaluation.dp.service.PokemonApiService;
import com.evaluation.dp.R;
import com.evaluation.dp.adapter.PokemonAdapter;
import com.evaluation.dp.model.Pokemon;
import com.evaluation.dp.model.PokemonResponse;
import com.evaluation.dp.service.ApiServicePokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPokemonList;
    private PokemonAdapter adapter;
    private int offset;
    private boolean loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvPokemonList =findViewById(R.id.rvPokemonList);
        rvPokemonList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PokemonAdapter(new ArrayList<>(), this);
        rvPokemonList.setAdapter(adapter);
        rvPokemonList.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        rvPokemonList.setLayoutManager(layoutManager);
        rvPokemonList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(loading) {
                        if((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loading = false;
                            offset += 20;
                            getPokemonData(offset);
                        }
                    }
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fabd1a")));
            if(getIntent().hasExtra("fullName")) {
                actionBar.setTitle("Bienvenido " + getIntent().getStringExtra("fullName"));
            }
        }


        loading = true;
        offset = 0;
        getPokemonData(offset);


    }

    private void getPokemonData(int offset) {
        ApiServicePokemon apiServicePokemon = PokemonApiService.getApiservicePokemon();
        Call<PokemonResponse> call = apiServicePokemon.getListPokemon(20,offset);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                loading = true;
                if(response.isSuccessful()) {
                    ArrayList<Pokemon> pokemonList = response.body().getResults();
                    adapter.setPokemonList(pokemonList);
                    adapter.setOnItemClickListener(new PokemonAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Pokemon pokemon) {
                            openPokemonDetails(pokemon.getNumber(), pokemon.getName());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                loading = true;
                Log.d("error", "onFailure: " +t);

            }
        });
    }

    private void openPokemonDetails(int number, String pokemonName) {
        Intent intent = new Intent(MainActivity.this, PokemonDetailActivity.class);
        intent.putExtra("number", number);
        intent.putExtra("pokemonName", pokemonName);
        startActivity(intent);
    }
}


