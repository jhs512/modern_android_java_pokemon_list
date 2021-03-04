package com.example.sbs.myapplication.api;

import com.example.sbs.myapplication.dto.Pokemon;

import java.util.List;

public class PokemonApi__getPokemons__ResponseBody {
    public int count;
    public String next;
    public String previous;
    public List<Pokemon> results;
}
