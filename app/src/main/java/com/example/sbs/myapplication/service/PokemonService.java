package com.example.sbs.myapplication.service;

import androidx.annotation.NonNull;

import com.example.sbs.myapplication.api.PokemonApi__getPokemons__ResponseBody;
import com.example.sbs.myapplication.api.PokemonApi;
import com.example.sbs.myapplication.util.Util;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonService {
    private PokemonApi pokemonApi;

    @Inject
    public PokemonService(PokemonApi pokemonApi) {
        this.pokemonApi = pokemonApi;
    }

    public void getPokemons(int offset, int limit, @NonNull Consumer<? super PokemonApi__getPokemons__ResponseBody> onNext) {
        pokemonApi.getPokemons(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, throwable -> Util.log("throwable : " + throwable.getMessage()));
    }
}
