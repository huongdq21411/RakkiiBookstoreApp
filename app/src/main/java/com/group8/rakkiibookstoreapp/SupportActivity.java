package com.group8.rakkiibookstoreapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group8.rakkiibookstoreapp.databinding.ActivitySupportBinding;

public class SupportActivity extends AppCompatActivity {

    ActivitySupportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.imvArrowDown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.txtShippingContent.getVisibility() == View.GONE) {
                    binding.txtShippingContent.setVisibility(View.VISIBLE);
                    binding.imvArrowDown1.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }else{
                    binding.txtShippingContent.setVisibility(View.GONE);
                    binding.imvArrowDown1.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }
            }
        });

        binding.imvArrowDown2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.txtWarrantyContent.getVisibility() == View.GONE) {
                    binding.txtWarrantyContent.setVisibility(View.VISIBLE);
                    binding.imvArrowDown2.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }else{
                    binding.txtWarrantyContent.setVisibility(View.GONE);
                    binding.imvArrowDown2.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
                }
            }
        });
    }
}