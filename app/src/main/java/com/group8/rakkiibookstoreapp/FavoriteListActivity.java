package com.group8.rakkiibookstoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;


import com.group8.rakkiibookstoreapp.adapter.BookListAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityFavoriteListBinding;
import com.group8.rakkiibookstoreapp.helper.WishList;

public class FavoriteListActivity extends AppCompatActivity {

    ActivityFavoriteListBinding binding;
    private WishList wishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        wishList = new WishList(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rvFavBooks), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        wishList = new WishList(this);

        binding.imvBack.setOnClickListener(v -> finish());
        initList();
    }

    private void initList() {
        binding.rvFavBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        BookListAdapter adapter = new BookListAdapter(wishList.getWishList());
        binding.rvFavBooks.setAdapter(adapter);


        String countText = String.format("%d", adapter.getItemCount());
        String fullText = String.format("Bạn có %s sản phẩm trong Danh sách Yêu thích", countText);
        SpannableString styledText = new SpannableString(fullText);
        styledText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(FavoriteListActivity.this, R.color.purple_Dark)), fullText.indexOf(countText), fullText.indexOf(countText) + countText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new StyleSpan(Typeface.BOLD), fullText.indexOf(countText), fullText.indexOf(countText) + countText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.txtCount.setText(styledText);
    }

}