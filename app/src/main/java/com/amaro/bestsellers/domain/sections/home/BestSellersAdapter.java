package com.amaro.bestsellers.domain.sections.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaro.bestsellers.R;
import com.amaro.bestsellers.domain.model.Product;
import com.bumptech.glide.Glide;

import java.util.List;

public class BestSellersAdapter extends RecyclerView.Adapter<BestSellersAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;
    private final OnItemClickListener listener;

    public BestSellersAdapter(Context ctx, List<Product> data, OnItemClickListener listener) {
        this.context = ctx;
        this.productList = data;
        this.listener = listener;
    }

    @Override
    public BestSellersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_product_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.click(productList.get(position), listener);
        holder.title.setText(productList.get(position).name);
        holder.price.setText(productList.get(position).actualPrice);

        Glide.with(context)
                .load(productList.get(position).getDisplayImage())
                .into(holder.background);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface OnItemClickListener {
        void onClick(Product product);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView background;
        TextView title;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            background = itemView.findViewById(R.id.imageView);
        }

        public void click(final Product product, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(product);
                }
            });
        }
    }
}
