package com.group8.rakkiibookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group8.rakkiibookstoreapp.databinding.ActivityBlogBinding;
import com.group8.rakkiibookstoreapp.databinding.ActivityDiscoverBinding;

public class DiscoverActivity extends AppCompatActivity {

    ActivityDiscoverBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDiscoverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_discover);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        binding.imvBack.setOnClickListener(v -> finish());
        Blog();
        Info();
        Voucher();
    }

    private void Voucher() {
        binding.btnVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiscoverActivity.this, VoucherActivity.class));
            }
        });
    }

    private void Info() {
        binding.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiscoverActivity.this, InfoActivity.class));
            }
        });
    }

    private void Blog() {
        binding.btnBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiscoverActivity.this, BlogActivity.class));
            }
        });
    }

    private void statusBarColor() {
        Window window = DiscoverActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(DiscoverActivity.this, R.color.purple_Dark));
    }
}