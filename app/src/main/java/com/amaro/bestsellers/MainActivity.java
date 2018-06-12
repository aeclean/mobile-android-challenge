package com.amaro.bestsellers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amaro.bestsellers.domain.model.Product;
import com.amaro.bestsellers.domain.model.ProductResponse;
import com.amaro.bestsellers.domain.networking.Service;
import com.amaro.bestsellers.domain.sections.home.BestSellersAdapter;
import com.amaro.bestsellers.domain.sections.home.HomeView;
import com.amaro.bestsellers.domain.sections.home.Presenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HomeView {

    @BindView(R.id.product_list) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Inject
    public Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDeps().inject(this);

        render();

        ButterKnife.bind(this);

        Presenter presenter = new Presenter(service, this);
        presenter.getProductList();
    }

    private void render() {
        setContentView(getLayout());
    }

    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String error) {
        Log.d("AMARO", error);
    }

    @Override
    public void onSuccess(ProductResponse response) {
        BestSellersAdapter adapter = new BestSellersAdapter(this, response.getData(), new BestSellersAdapter.OnItemClickListener() {
            @Override
            public void onClick(Product item) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("product", item);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
