package com.amaro.bestsellers.domain.sections.details;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaro.bestsellers.DetailActivity;
import com.amaro.bestsellers.R;
import com.amaro.bestsellers.domain.model.Product;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Presenter {

    private final DetailView view;
    private final DetailActivity context;

    @BindView(R.id.image_display)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.actual_price)
    TextView actualPrice;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.sizes)
    TextView sizes;

    public Presenter(DetailActivity ctx, DetailView v) {
        this.view = v;
        this.context = ctx;

        ButterKnife.bind(this, context);
    }

    public void populateData(Product product) {
        Glide
                .with(context)
                .load(product.getDisplayImage())
                .into(image);

        title.setText(product.name);

        status.setText(String.format("Disponível: %s", product.isOnSale() ? "Sim" : "Não"));
        price.setText(product.regularPrice);

        actualPrice.setText(product.actualPrice);

        if(!product.discountPercentage.isEmpty()) {
            actualPrice.setVisibility(View.VISIBLE);
            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            price.setTextColor(Color.LTGRAY);
        } else {
            actualPrice.setVisibility(View.GONE);
        }

        sizes.setText(product.getAvailableSizes());
    }
}
