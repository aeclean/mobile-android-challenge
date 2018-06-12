package com.amaro.bestsellers;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaro.bestsellers.domain.model.Product;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        render();

        ButterKnife.bind(this);

        if(getIntent().getExtras().containsKey("product")) {
            Product product = getIntent().getExtras().getParcelable("product");

            Glide
                    .with(this)
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

            Log.d("AMARO", product.name);
        }
    }

    private void render() {
        setContentView(getLayout());
    }

    @Override
    int getLayout() {
        return R.layout.detail_activity;
    }
}
