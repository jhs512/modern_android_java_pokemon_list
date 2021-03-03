package com.example.sbs.myapplication.di;

import com.example.sbs.myapplication.BuildConfig;
import com.example.sbs.myapplication.api.PokemonApi;
import com.example.sbs.myapplication.service.PokemonService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


@Module
@InstallIn(SingletonComponent.class)
public class SingletonModule {
    @Provides
    @Singleton
    public static PokemonService providePokemonService(PokemonApi pokemonApi) {
        return new PokemonService(pokemonApi);
    }

    @Provides
    @Singleton
    public static PokemonApi providePokemonApi(Retrofit.Builder retrofitBuilder) {
        Retrofit retrofit = retrofitBuilder
                .baseUrl(PokemonApi.baseUrl)
                .build();

        return retrofit.create(PokemonApi.class);
    }

    @Provides
    public static Retrofit.Builder provideRetrofitBuilder() {
        OkHttpClient okHttpClient = null;

        if (BuildConfig.DEBUG) {
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor()) // Stetho Interceptor 추가해야 Chrome Inspect tool 에서 확인 가능, 필수 아님
                    .build();
        }

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create()) // Jackson 사용
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()); // RX Java 사용

        if (okHttpClient != null) {
            retrofitBuilder.client(okHttpClient);
        }

        return retrofitBuilder;
    }
}
