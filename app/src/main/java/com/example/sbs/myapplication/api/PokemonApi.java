package com.example.sbs.myapplication.api;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonApi {
    String baseUrl = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Observable<PokemonApi__getPokemons__ResponseBody> getPokemons(@Query("offset") int offset, @Query("limit") int limit);
}