package com.group8.rakkiibookstoreapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group8.rakkiibookstoreapp.DetailActivity;
import com.group8.rakkiibookstoreapp.databinding.ItemLayoutBinding;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.Viewholder> {
    ArrayList<BookList> items;
    Context context;
    ItemLayoutBinding binding;

    public BookListAdapter(ArrayList<BookList> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public BookListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
        binding.txtTitle.setText(items.get(position).getTitle());
        binding.txtPrice.setText(items.get(position).getPrice() + "đ");
        binding.txtScore.setText(items.get(position).getScore() + "");
        binding.imvProduct.getResources().getIdentifier(items.get(position).getPicUrl(),
                "drawable", binding.imvProduct.getContext().getPackageName());


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
        public Viewholder(ItemLayoutBinding binding) {
            super(binding.getRoot());
        }
    }
}
