package com.evaluation.dp.service;

import com.evaluation.dp.service.ApiServicePokemon;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonApiService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    private static Retrofit retrofit = null;

    public static ApiServicePokemon getApiservicePokemon() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiServicePokemon.class);
    }
}
