package com.group8.rakkiibookstoreapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.group8.rakkiibookstoreapp.databinding.ActivityDetailBinding;
import com.group8.rakkiibookstoreapp.model.PopularProduct;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private PopularProduct object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundles(); //ông include thêm func dành cho BookList object dc ko á
    }

    private void getBundles() {
        PopularProduct object = (PopularProduct) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());
        Glide.with(this)

                .load(drawableResourceId)

                .into(binding.imvProduct);

        binding.txtTitle.setText(object.getTitle());

        binding.txtPrice.setText(String.valueOf((int) object.getPrice()) + " đ");

        binding.txtDescription.setText(object.getDescription());

        binding.txtReviews.setText(object.getReview()+"");

        binding.txtRating.setText(object.getScore()+"");

        binding.imvBack.setOnClickListener(v -> finish());


    }
}
