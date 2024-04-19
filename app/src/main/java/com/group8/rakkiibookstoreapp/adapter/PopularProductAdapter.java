package com.group8.rakkiibookstoreapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.group8.rakkiibookstoreapp.databinding.ViewholderPupListBinding;
import com.group8.rakkiibookstoreapp.model.PopularProduct;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.Viewholder> {
    ArrayList<PopularProduct> items;
    Context context;
    ViewholderPupListBinding binding;

    public PopularProductAdapter(ArrayList<PopularProduct> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularProductAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderPupListBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductAdapter.Viewholder holder, int position) {
        binding.txtTitle.setText(items.get(position).getTitle());
        binding.txtPrice.setText(items.get(position).getPrice() + "đ");
        binding.txtScore.setText(items.get(position).getScore() + "");

        int drawableResourced =
                holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),
                        "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(binding.imvProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(ViewholderPupListBinding binding) {
            super(binding.getRoot());
        }
    }
}
