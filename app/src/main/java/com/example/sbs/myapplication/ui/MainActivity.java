package com.example.sbs.myapplication.ui;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sbs.myapplication.R;
import com.example.sbs.myapplication.databinding.ActivityMainBinding;
import com.example.sbs.myapplication.databinding.NavHeaderMainBinding;
import com.example.sbs.myapplication.util.Util;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 메인바인딩 생성 및 세팅
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mainBinding.setLifecycleOwner(this);

        // 메인뷰모델 생성 및 세팅
        MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);
        mainBinding.setVm(vm);
        setContentView(mainBinding.getRoot());

        // 툴바 세팅
        setSupportActionBar(mainBinding.appBarMain.toolbar);

        // FAB 세팅
        mainBinding.appBarMain.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        // 네비게이션 드로어 세팅
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header_main, mainBinding.navView, false);
        navHeaderMainBinding.setLifecycleOwner(this);

        navHeaderMainBinding.setVm(vm);
        mainBinding.navView.addHeaderView(navHeaderMainBinding.getRoot());

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(mainBinding.drawerLayout)
                .build();
        NavController navController = Util.getMainNavController(this);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mainBinding.navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Util.getMainNavController(this), mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}