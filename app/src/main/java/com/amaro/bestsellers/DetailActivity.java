package com.amaro.bestsellers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amaro.bestsellers.domain.model.Product;
import com.amaro.bestsellers.domain.sections.details.DetailView;
import com.amaro.bestsellers.domain.sections.details.Presenter;

public class DetailActivity extends BaseActivity implements DetailView {

    private Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        render();

        if(getIntent().getExtras().containsKey("product")) {
            Product product = getIntent().getExtras().getParcelable("product");

            presenter = new Presenter(this, this);
            presenter.populateData(product);
        }
    }

    private void render() {
        setContentView(getLayout());
    }

    @Override
    int getLayout() {
        return R.layout.detail_activity;
    }

    @Override
    public void populateData(Product item) {
        //
    }
}
