package com.example.sbs.myapplication.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sbs.myapplication.dto.Pokemon;
import com.example.sbs.myapplication.service.PokemonService;
import com.example.sbs.myapplication.util.Util;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeMainViewModel extends AndroidViewModel {
    public RecyclerViewPokemonAdapter recyclerViewPokemonAdapter = new RecyclerViewPokemonAdapter();
    private PokemonService pokemonService;

    @Inject
    public HomeMainViewModel(@NonNull Application application, PokemonService pokemonService) {
        super(application);

        Util.log("on HomeMainViewModel");

        this.pokemonService = pokemonService;

        pokemonService.getPokemons(0, 20, responseBody -> {
            recyclerViewPokemonAdapter.addPokemons(responseBody.results);
        });

        Util.setTimeout(() -> {
            pokemonService.getPokemons(20, 20, responseBody -> {
                recyclerViewPokemonAdapter.addPokemons(responseBody.results);
            });
        }, 10000);
    }
}