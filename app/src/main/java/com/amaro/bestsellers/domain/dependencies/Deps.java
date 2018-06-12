package com.amaro.bestsellers.domain.dependencies;

import com.amaro.bestsellers.BaseActivity;
import com.amaro.bestsellers.MainActivity;
import com.amaro.bestsellers.domain.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(MainActivity mainActivity);
}
