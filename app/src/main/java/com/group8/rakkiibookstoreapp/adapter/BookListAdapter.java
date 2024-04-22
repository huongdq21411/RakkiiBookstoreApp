package com.group8.rakkiibookstoreapp.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.group8.rakkiibookstoreapp.DetailActivity;
import com.group8.rakkiibookstoreapp.R;
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

        ConstraintLayout itemContent = holder.itemView.findViewById(R.id.itemContent);
        itemContent.setOnClickListener(new View.OnClickListener() {
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
        private boolean isLiked = false;

        public Viewholder(ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            MaterialButton btnLike = itemView.findViewById(R.id.btnLike);
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int defaultColor = ContextCompat.getColor(btnLike.getContext(), R.color.unliked);
                    int newColor = ContextCompat.getColor(btnLike.getContext(), R.color.liked);
                    int new_iconResId = btnLike.getResources().getIdentifier("baseline_bookmark_24", "drawable", btnLike.getContext().getPackageName());
                    Drawable new_icon = btnLike.getResources().getDrawable(new_iconResId, null);
                    int iconResId = btnLike.getResources().getIdentifier("baseline_bookmark_border_24", "drawable", btnLike.getContext().getPackageName());
                    Drawable icon = btnLike.getResources().getDrawable(iconResId, null);

                    isLiked = !isLiked;
                    if (isLiked) {
                        btnLike.setIcon(new_icon);
                        btnLike.setCompoundDrawableTintList(ColorStateList.valueOf(newColor));
                    } else {
                        btnLike.setIcon(icon);
                        btnLike.setCompoundDrawableTintList(ColorStateList.valueOf(defaultColor));
                    }
                    showLikeMessage();
                }
            });
        }
        private void showLikeMessage() {
            String message = isLiked ? "Đã thêm vào Yêu thích" : "Đã bỏ khỏi Yêu thích";
            Toast.makeText(itemView.getContext(), message, Toast.LENGTH_SHORT).show();
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
