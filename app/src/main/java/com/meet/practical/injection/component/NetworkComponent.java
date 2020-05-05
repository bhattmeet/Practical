package com.meet.practical.injection.component;

import android.app.Application;
import android.content.SharedPreferences;

import com.meet.practical.injection.module.AppModule;
import com.meet.practical.injection.module.NetworkModule;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {

    Retrofit provideRetrofit();
    Application provideAppContext();
    SharedPreferences provideSharedPreference();
    Gson provideGson();

}
