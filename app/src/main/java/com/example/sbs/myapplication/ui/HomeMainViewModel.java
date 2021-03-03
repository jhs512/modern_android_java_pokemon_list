package com.example.sbs.myapplication.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sbs.myapplication.dto.Pokemon;
import com.example.sbs.myapplication.service.PokemonService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeMainViewModel extends AndroidViewModel {
    public MutableLiveData<List<Pokemon>> lvPokemons = new MutableLiveData<>();
    private PokemonService pokemonService;

    @Inject
    public HomeMainViewModel(@NonNull Application application, PokemonService pokemonService) {
        super(application);
        this.pokemonService = pokemonService;
    }

    public void initView() {
        pokemonService.getPokemons(0, 20, responseBody -> {
            lvPokemons.setValue(responseBody.getResults());
        });
    }
}