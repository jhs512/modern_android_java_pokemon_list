package com.example.sbs.myapplication.dto;

import com.example.sbs.myapplication.R;
import com.example.sbs.myapplication.ui.PokemonListItemViewModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Pokemon {
    public int id;
    public String name;
    public Map<Integer, Object> itemViewModels;

    public String getImgUrl() {
        return "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";
    }

    @JsonCreator
    public Pokemon(@JsonProperty("name") String name, @JsonProperty("url") String url) {
        this.name = name;
        String[] urlBits = url.split("/");
        this.id = Integer.parseInt(urlBits[urlBits.length - 1]);
    }

    public <T> T getItemViewModel(int layoutId) {
        if ( itemViewModels == null ) {
            itemViewModels = new HashMap<>();
        }

        if ( itemViewModels.get(R.layout.item_pokemon_content) == null ) {
            itemViewModels.put(R.layout.item_pokemon_content, new PokemonListItemViewModel(this));
        }

        return (T)itemViewModels.get(R.layout.item_pokemon_content);
    }
}