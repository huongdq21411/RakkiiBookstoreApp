package com.group8.rakkiibookstoreapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.group8.rakkiibookstoreapp.DetailActivity;
import com.group8.rakkiibookstoreapp.R;
import com.group8.rakkiibookstoreapp.databinding.WishlistLayoutBinding;
import com.group8.rakkiibookstoreapp.helper.ManagmentCart;
import com.group8.rakkiibookstoreapp.helper.WishList;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.Viewholder> {
    ArrayList<BookList> items;
    WishlistLayoutBinding binding;
    Context context;
    ManagmentCart managmentCart;
    WishList wishList;
    private int numberOrder = 1;
    OnDataSetChangedListener onDataSetChangedListener;

    public WishListAdapter(ArrayList<BookList> items, OnDataSetChangedListener onDataSetChangedListener) {
        this.items = items;
        this.onDataSetChangedListener = onDataSetChangedListener;
    }

    @NonNull
    @Override
    public WishListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = WishlistLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        managmentCart = new ManagmentCart(context);
        wishList = new WishList(context);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.Viewholder holder, int position) {
        BookList item = items.get(position);

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
        holder.setRemoveFromWishlist(item);

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
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface OnDataSetChangedListener {
        void onDataSetChanged(int newCount);
    }
    public void dataSetChanged() {
        notifyDataSetChanged();
        if (onDataSetChangedListener != null) {
            onDataSetChangedListener.onDataSetChanged(getItemCount());
        }
    }
    public void setOnDataSetChangedListener(OnDataSetChangedListener listener) {
        this.onDataSetChangedListener = listener;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public Viewholder(WishlistLayoutBinding binding) {
            super(binding.getRoot());
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
        public void setRemoveFromWishlist(BookList object) {
            int position = items.indexOf(object);
            if (position != -1) {
                binding.btnDelete.setOnClickListener(v -> {
                    wishList.removefromWishlist(items, position);
                    notifyItemRemoved(position);
                    int range = items.isEmpty() ? 0 : Math.max(1, items.size() - position);
                    notifyItemRangeChanged(0, items.size());
                    if (onDataSetChangedListener != null) {
                        onDataSetChangedListener.onDataSetChanged(getItemCount());
                    }
                });
            }
        }
    }
}
