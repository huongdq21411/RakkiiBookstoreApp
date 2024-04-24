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
import com.group8.rakkiibookstoreapp.helper.ManagmentCart;
import com.group8.rakkiibookstoreapp.helper.WishList;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.Viewholder> {
    ArrayList<BookList> items;
    Context context;
    ItemLayoutBinding binding;
    private ManagmentCart managmentCart;
    private WishList wishList;
    private int numberOrder = 1;


    public BookListAdapter(ArrayList<BookList> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public BookListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        managmentCart = new ManagmentCart(context);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
        BookList item = items.get(position);
        holder.currentItem = item;

        String title = item.getTitle();
        holder.setTitle(title);
        double price = item.getPrice();
        holder.setPrice(price);
        String score = String.valueOf(item.getScore());
        holder.setScore(score);
        String category = item.getCategory();
        holder.setCategory(category);
        String picUrl = item.getPicUrl();
        holder.setThumb(picUrl);

        holder.setAddToCart(item);
        holder.setAddtoWishlist(item);

        ConstraintLayout itemContent = holder.itemView.findViewById(R.id.itemContent);
        itemContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", item);
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
        public BookList currentItem;

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
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
            DecimalFormat decimalFormat = (DecimalFormat)nf;
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
        public void setAddToCart(BookList object) {
            binding.btnAddToCart.setOnClickListener(v -> {
                object.setNumberInCart(numberOrder);
                managmentCart.insertFood(object);
            });
        }
        public void setAddtoWishlist(BookList object) {
            MaterialButton btnLike = itemView.findViewById(R.id.btnLike);
            btnLike.setOnClickListener(v -> {
                boolean isLiked = !object.isLiked();
                object.setLiked(isLiked);
                int defaultColor = ContextCompat.getColor(v.getContext(), R.color.unliked);
                int newColor = ContextCompat.getColor(v.getContext(), R.color.liked);
                int new_iconResId = v.getResources().getIdentifier("baseline_bookmark_24", "drawable", v.getContext().getPackageName());
                Drawable new_icon = v.getResources().getDrawable(new_iconResId, null);
                int iconResId = v.getResources().getIdentifier("baseline_bookmark_border_24", "drawable", v.getContext().getPackageName());
                Drawable icon = v.getResources().getDrawable(iconResId, null);

                if (isLiked) {
                    btnLike.setIcon(new_icon);
                    btnLike.setCompoundDrawableTintList(ColorStateList.valueOf(newColor));
                    wishList.addtoWishlist(object);
                } else {
                    btnLike.setIcon(icon);
                    btnLike.setCompoundDrawableTintList(ColorStateList.valueOf(defaultColor));
                    wishList.removefromWishlist(object);
                }

                String message = isLiked ? "Đã thêm vào Yêu thích" : "Đã bỏ khỏi Yêu thích";
                Toast.makeText(itemView.getContext(), message, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
