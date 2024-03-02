package com.evaluation.dp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServicePokemon {
@GET("pokemon")
    Call<PokeApi> getData();
}
