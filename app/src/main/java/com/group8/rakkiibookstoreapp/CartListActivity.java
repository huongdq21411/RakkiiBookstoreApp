package com.group8.rakkiibookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.group8.rakkiibookstoreapp.adapter.CartAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityCartBinding;
import com.group8.rakkiibookstoreapp.databinding.ActivityCartListBinding;
import com.group8.rakkiibookstoreapp.helper.ChangeNumberItemsListener;
import com.group8.rakkiibookstoreapp.helper.ManagmentCart;

import java.util.ArrayList;

public class CartListActivity extends AppCompatActivity {

    ActivityCartListBinding binding;
    private ManagmentCart managmentCart;
    double tax;
    ArrayAdapter<String> adapter;
    ArrayList<String> address;
    ArrayList<String> payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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

        binding.btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity
                        .this, CartActivity.class));
            }
        });

    }

    private void statusBarColor() {
        Window window = CartListActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CartListActivity.this, R.color.purple_Dark));
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
        double total = Math.round((managmentCart.getTotalFee()));
        double itemTotal = Math.round(managmentCart.getTotalFee());
        binding.txtTotal.setText(total + " đ");
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