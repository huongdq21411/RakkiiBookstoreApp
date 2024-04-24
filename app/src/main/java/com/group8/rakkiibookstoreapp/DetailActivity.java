package com.group8.rakkiibookstoreapp;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.group8.rakkiibookstoreapp.databinding.ActivityDetailBinding;
import com.group8.rakkiibookstoreapp.helper.ManagmentCart;
import com.group8.rakkiibookstoreapp.model.BookList;

import java.io.Serializable;
import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private BookList object;
    private int numberOrder =1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundles();
        managmentCart = new ManagmentCart(this);
        statusBarColor();
    }

    private void statusBarColor() {
        Window window = DetailActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(DetailActivity.this, R.color.purple_Dark));
    }

    private void getBundles() {
        object = (BookList) getIntent().getSerializableExtra("object");
            int drawableResourceId = this.getResources().getIdentifier(((BookList) object).getPicUrl(), "drawable", this.getPackageName());
            Glide.with(this)

                    .load(drawableResourceId)

                    .into(binding.imvProduct);
            binding.txtTitle.setText(((BookList) object).getTitle());
            DecimalFormat decimalFormat = new DecimalFormat("#.##0");
            String formattedPrice = decimalFormat.format(((BookList) object).getPrice());
            binding.txtPrice.setText(formattedPrice + " đ");
            binding.txtDescription.setText(((BookList) object).getDescription());
            binding.txtReviews.setText(((BookList) object).getReview()+"");
            binding.txtRating.setText(((BookList) object).getScore()+"");
            binding.btnAddToCart.setOnClickListener(v -> {
                object.setNumberInCart(numberOrder);
                managmentCart.insertFood(object);
            });

        binding.imvBack.setOnClickListener(v -> finish());
    }
}
