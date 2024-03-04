package com.evaluation.dp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evaluation.dp.service.PokemonApiService;
import com.evaluation.dp.R;
import com.evaluation.dp.adapter.PokemonAbilityAdapter;
import com.evaluation.dp.model.Abilities;
import com.evaluation.dp.model.PokemonDetailResponse;
import com.evaluation.dp.service.ApiServicePokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends AppCompatActivity {

    private TextView tvPokemonNameDetail, tvbaseExperience,tvHeight, tvabilities;
    private ImageView ivPokemonDetailImage;
    private RecyclerView rvabilitiesList;
    private PokemonAbilityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        tvPokemonNameDetail = findViewById(R.id.tvPokemonNameDetail);
        tvbaseExperience = findViewById(R.id.tvbaseExperience);
        tvHeight = findViewById(R.id.tvHeight);
        tvabilities = findViewById(R.id.tvAbilitiesTitle);
        ivPokemonDetailImage = findViewById(R.id.ivPokemonDetailImage);

        rvabilitiesList = findViewById(R.id.rvAbilitiesList);
        rvabilitiesList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PokemonAbilityAdapter(new ArrayList<>());
        rvabilitiesList.setAdapter(adapter);


        if(getIntent().hasExtra("number")) {
            int numberPokemon = getIntent().getIntExtra("number", -1);
            String pokemonName = getIntent().getStringExtra("pokemonName");
            tvPokemonNameDetail.setText(pokemonName);
            getDetailPokemon(numberPokemon);

            Glide.with(this)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+numberPokemon +".png")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(ivPokemonDetailImage);
        }
    }

    private void getDetailPokemon(int numberPokemon) {
        ApiServicePokemon apiServicePokemon = PokemonApiService.getApiservicePokemon();
        Call<PokemonDetailResponse> call = apiServicePokemon.getDetailsPokemon(numberPokemon);

        call.enqueue(new Callback<PokemonDetailResponse>() {

            @Override
            public void onResponse(Call<PokemonDetailResponse> call, Response<PokemonDetailResponse> response) {
               tvbaseExperience.setText("" + response.body().getBase_experience());
               tvHeight.setText("" + response.body().getHeight());
                ArrayList<Abilities> abilities = response.body().getAbilities();
                adapter.setPokemonAbilities(abilities);

            }

            @Override
            public void onFailure(Call<PokemonDetailResponse> call, Throwable t) {

            }
        });
    }
}