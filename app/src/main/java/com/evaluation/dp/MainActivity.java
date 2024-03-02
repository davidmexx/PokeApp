package com.evaluation.dp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.rvPokemonList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PokemonAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        getPokemonData();


    }

    private void getPokemonData() {
        ApiServicePokemon apiServicePokemon = ApiClient.getApiservicePokemon();
        Call<PokeApi> call = apiServicePokemon.getData();
        call.enqueue(new Callback<PokeApi>() {
            @Override
            public void onResponse(Call<PokeApi> call, Response<PokeApi> response) {
                if(response.isSuccessful()) {
                    List<Pokemon> pokemonList = response.body().getResults();
                    Log.d("prueba", "onResponse: " + pokemonList);
                    adapter.setPokemonList(pokemonList);
                    adapter.setOnItemClickListener(new PokemonAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Pokemon pokemon) {
                            openPokemonDetails(pokemon.getUrl());
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PokeApi> call, Throwable t) {
                Log.d("prueba", "onFailure: " +t);

            }
        });
    }

    private void openPokemonDetails(String PokemonUrl) {
        Intent intent = new Intent(MainActivity.this, PokemonDetail.class);
        intent.putExtra("pokemonUrl", PokemonUrl);
        startActivity(intent);
    }
}


