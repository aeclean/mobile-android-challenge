package com.amaro.bestsellers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amaro.bestsellers.domain.dependencies.DaggerDeps;
import com.amaro.bestsellers.domain.dependencies.Deps;
import com.amaro.bestsellers.domain.networking.NetworkModule;

import java.io.File;

public abstract class BaseActivity extends AppCompatActivity {

    Deps deps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File cacheFile = new File(getCacheDir(), "results");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }


    public Deps getDeps() {
        return deps;
    }

    abstract int getLayout();
}
