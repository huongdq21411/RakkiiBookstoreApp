package com.group8.rakkiibookstoreapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group8.rakkiibookstoreapp.DetailActivity;
import com.group8.rakkiibookstoreapp.databinding.ItemLayoutBinding;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.text.DecimalFormat;
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
        String title = items.get(position).getTitle();
        holder.setTitle(title);
        double price = items.get(position).getPrice();
        holder.setPrice(price);
        String score = String.valueOf(items.get(position).getScore());
        holder.setScore(score);
        String category = items.get(position).getCategory();
        holder.setCategory(category);
        String picUrl = items.get(position).getPicUrl();
        holder.setThumb(picUrl);

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
        private final ItemLayoutBinding binding;

        public Viewholder(ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setTitle(String title) {
            int maxLines = 3;
            binding.txtTitle.setText(title);
            binding.txtTitle.setMaxLines(maxLines);
            binding.txtTitle.setEllipsize(TextUtils.TruncateAt.END);
        }
        public void setPrice(double price) {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            String formattedPrice = decimalFormat.format(price);
            binding.txtPrice.setText(formattedPrice + " đ");
        }
        public void setScore(String score) {
            binding.txtScore.setText(score);
        }
        public void setCategory(String category) {
            binding.txtCategory.setText(category);
        }
        public void setThumb(String picUrl) {
            int resourceId = binding.imvProduct.getResources().getIdentifier(picUrl, "drawable", binding.imvProduct.getContext().getPackageName());
            if (resourceId == 0) {
                Log.e("ImageBinding", "Resource not found for PicUrl: " + picUrl);
            } else {
                binding.imvProduct.setImageResource(resourceId);
            }
        }
    }
}
