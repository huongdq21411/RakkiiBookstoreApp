package com.group8.rakkiibookstoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.group8.rakkiibookstoreapp.adapter.WishListAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityFavoriteListBinding;
import com.group8.rakkiibookstoreapp.helper.WishList;

public class FavoriteListActivity extends AppCompatActivity implements WishListAdapter.OnDataSetChangedListener {

    ActivityFavoriteListBinding binding;
    private WishList wishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        wishList = new WishList(this);

        binding.imvBack.setOnClickListener(v -> finish());
        initList();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initList() {
        binding.rvFavBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        WishListAdapter wishListAdapter = new WishListAdapter(wishList.getWishList(), this);
        binding.rvFavBooks.setAdapter(wishListAdapter);

        int currentItemCount = wishListAdapter.getItemCount();
        updateItemCountTextView(currentItemCount);
        wishListAdapter.dataSetChanged();
    }

    private void updateItemCountTextView(int itemCount) {
        String countText = String.format("%d", itemCount);
        String fullText = String.format("Bạn có %s sản phẩm trong Danh sách Yêu thích", countText);
        SpannableString styledText = new SpannableString(fullText);
        styledText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_Dark)), fullText.indexOf(countText), fullText.indexOf(countText) + countText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new StyleSpan(Typeface.BOLD), fullText.indexOf(countText), fullText.indexOf(countText) + countText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.txtCount.setText(styledText);
    }

    @Override
    public void onDataSetChanged(int newCount) {
        String countText = String.format("%d", newCount);
        String fullText = String.format("Bạn có %s sản phẩm trong Danh sách Yêu thích", countText);
        SpannableString styledText = new SpannableString(fullText);
        styledText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_Dark)), fullText.indexOf(countText), fullText.indexOf(countText) + countText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new StyleSpan(Typeface.BOLD), fullText.indexOf(countText), fullText.indexOf(countText) + countText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.txtCount.setText(styledText);
    }
}