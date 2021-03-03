package com.example.sbs.myapplication.api;

import com.example.sbs.myapplication.dto.Pokemon;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonApi__getPokemons__ResponseBody {
    private int count;
    private String next;
    private String previous;
    private List<Pokemon> results;
}
