package com.example.sbs.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sbs.myapplication.R;
import com.example.sbs.myapplication.databinding.FragmentHomeMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeMainFragment extends Fragment {

    private HomeMainViewModel homeMainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeMainViewModel =
                new ViewModelProvider(this).get(HomeMainViewModel.class);
        FragmentHomeMainBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_home_main, container, false);
        binding.setLifecycleOwner(this);
        binding.setVm(homeMainViewModel);

        RecyclerViewPokemonAdapter recyclerViewPokemonAdapter = new RecyclerViewPokemonAdapter();
        binding.fragmentHomeMainRecyclerViewPokemon.setAdapter(recyclerViewPokemonAdapter);

        homeMainViewModel.lvPokemons.observe(getViewLifecycleOwner(), pokemons -> {
            recyclerViewPokemonAdapter.setData(pokemons);
        });

        homeMainViewModel.initView();

        return binding.getRoot();
    }
}