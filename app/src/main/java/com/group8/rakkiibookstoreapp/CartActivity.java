package com.group8.rakkiibookstoreapp;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.group8.rakkiibookstoreapp.adapter.CartAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityCartBinding;
import com.group8.rakkiibookstoreapp.helper.ChangeNumberItemsListener;
import com.group8.rakkiibookstoreapp.helper.ManagmentCart;

public class CartActivity extends AppCompatActivity {

    private ManagmentCart managmentCart;
    ActivityCartBinding binding;
    double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        managmentCart = new ManagmentCart(this);
        setVariable();
        initList();
        calculatorCart();
        statusBarColor();
    }

    private void statusBarColor() {
        Window window = CartActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CartActivity.this, R.color.purple_Dark));
    }

    private void initList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.txtEmpty.setVisibility(View.VISIBLE);
            binding.scroll.setVisibility(View.GONE);
        }else{
            binding.txtEmpty.setVisibility(View.GONE);
            binding.scroll.setVisibility(View.VISIBLE);
        }

        binding.rcvCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rcvCart.setAdapter(new CartAdapter(managmentCart.getListCart(), new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculatorCart();
            }
        }));
    }

    private void calculatorCart() {
        double percentTax = 0.01;
        double delivery = 10;
        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100) / 100;

        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee()*100/100);
        binding.txtTotalFee.setText("đ" + itemTotal);
        binding.txtTotalTax.setText("đ" + tax);
        binding.txtDelivery.setText("đ" + delivery);
        binding.txtTotal.setText("đ" + total);
    }

    private void setVariable() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}