package com.group8.rakkiibookstoreapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.group8.rakkiibookstoreapp.databinding.ActivityDetailBinding;
import com.group8.rakkiibookstoreapp.model.BookList;
import com.group8.rakkiibookstoreapp.model.PopularProduct;

import java.io.Serializable;
import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundles();
    }

    private void getBundles() {
        //PopularProduct object = (PopularProduct) getIntent().getSerializableExtra("object");
        Serializable object = getIntent().getSerializableExtra("object");
        if (object instanceof PopularProduct) {
            int drawableResourceId = this.getResources().getIdentifier(((PopularProduct) object).getPicUrl(), "drawable", this.getPackageName());
            Glide.with(this)

                    .load(drawableResourceId)

                    .into(binding.imvProduct);
            binding.txtTitle.setText(((PopularProduct) object).getTitle());
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            String formattedPrice = decimalFormat.format(((PopularProduct) object).getPrice());
            binding.txtPrice.setText(formattedPrice + " đ");
            binding.txtDescription.setText(((PopularProduct) object).getDescription());
            binding.txtReviews.setText(((PopularProduct) object).getReview()+"");
            binding.txtRating.setText(((PopularProduct) object).getScore()+"");
        } else if (object instanceof BookList) {
            int drawableResourceId = this.getResources().getIdentifier(((BookList) object).getPicUrl(), "drawable", this.getPackageName());
            Glide.with(this)

                    .load(drawableResourceId)

                    .into(binding.imvProduct);
            binding.txtTitle.setText(((BookList) object).getTitle());
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            String formattedPrice = decimalFormat.format(((BookList) object).getPrice());
            binding.txtPrice.setText(formattedPrice + " đ");
            binding.txtDescription.setText(((BookList) object).getDescription());
            binding.txtReviews.setText(((BookList) object).getReview()+"");
            binding.txtRating.setText(((BookList) object).getScore()+"");
        }

        binding.imvBack.setOnClickListener(v -> finish());
    }
}
