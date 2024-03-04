package com.evaluation.dp.service;

import com.evaluation.dp.model.PokemonDetailResponse;
import com.evaluation.dp.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServicePokemon {
@GET("pokemon")
    Call<PokemonResponse> getListPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{number}")
    Call<PokemonDetailResponse> getDetailsPokemon(@Path("number") int number);

}
