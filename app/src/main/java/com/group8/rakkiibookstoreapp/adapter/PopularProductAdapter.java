package com.group8.rakkiibookstoreapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.group8.rakkiibookstoreapp.DetailActivity;
import com.group8.rakkiibookstoreapp.databinding.ViewholderPupListBinding;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.Viewholder> {
    ArrayList<BookList> items;
    Context context;
    ViewholderPupListBinding binding;

    public PopularProductAdapter(ArrayList<BookList> items) {
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
    public void onBindViewHolder(@NonNull PopularProductAdapter.Viewholder holder, @SuppressLint(
            "RecyclerView") int position) {
        binding.txtTitle.setText(items.get(position).getTitle());
        //format lại giá ở dạng: 100,000 đ
        double price = items.get(position).getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String formattedPrice = decimalFormat.format(price);
        binding.txtPrice.setText(formattedPrice + " đ");
        //binding.txtPrice.setText(items.get(position).getPrice() + "đ");
        binding.txtScore.setText(items.get(position).getScore() + "");
        binding.txtReview.setText(items.get(position).getReview() + "");

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
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", items.get(position));
                context.startActivity(intent);

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
