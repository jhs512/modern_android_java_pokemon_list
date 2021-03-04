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
import com.example.sbs.myapplication.util.Util;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeMainFragment extends Fragment {

    private HomeMainViewModel homeMainViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Util.log("onCreateView");

        homeMainViewModel =
                new ViewModelProvider(this).get(HomeMainViewModel.class);
        FragmentHomeMainBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_home_main, container, false);
        binding.setLifecycleOwner(this);
        binding.setVm(homeMainViewModel);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        Util.log("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Util.log("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Util.log("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Util.log("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Util.log("onDestroyView");
    }
}