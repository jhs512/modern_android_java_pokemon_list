package com.example.sbs.myapplication.ui;

import android.os.Bundle;

import com.example.sbs.myapplication.R;
import com.example.sbs.myapplication.dto.Pokemon;
import com.example.sbs.myapplication.ui.gallery.GalleryFragmentArgs;
import com.example.sbs.myapplication.util.Util;

public class PokemonListItemViewModel {
    public Pokemon pokemon;
    public PokemonListItemViewModel(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void goToDetailPage() {
        Bundle bundle = new Bundle();
        bundle.putInt("id", pokemon.id);
        
        Util.getMainNavController().navigate(R.id.nav_gallery, bundle);
    }
}
