package com.group8.rakkiibookstoreapp;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;

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

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ManagmentCart managmentCart;
    ActivityCartBinding binding;
    double tax;

    ArrayAdapter<String> adapter;
    ArrayList<String> address;
    ArrayList<String> payment;

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
        loadDataAddress();
        loadDataPayment();
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
        double delivery = 10000;
        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100) / 100;

        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee()*100/100);
        binding.txtTotalFee.setText(itemTotal + " đ");
        binding.txtTotalTax.setText(tax + " đ");
        binding.txtDelivery.setText(delivery + " đ");
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

    private void loadDataAddress() {

        address = new ArrayList<>();
        address.add("Tỉnh/Thành phố");
        address.add("TP. Hồ Chí Minh");
        address.add("Tp. Hà Nội");
        address.add("Cần Thơ");
        address.add("Bình Dương");
        address.add("An Giang");
        address.add("Thủ Đức");
        address.add("Đà Nẵng");
        address.add("Đồng Tháp");
        address.add("Tiền Giang");
        address.add("Long An");
        address.add("Đà Lạt");
        address.add("Vũng Tàu");
        address.add("Kiên Giang");
        address.add("Sóc Trăng");
        address.add("Long Xuyên");

        adapter = new ArrayAdapter<>(CartActivity.this, android.R.layout.simple_list_item_1, address);

        binding.spAddress.setAdapter(adapter);
    }

    private void loadDataPayment() {
        payment = new ArrayList<>();
        payment.add("Chọn phương thức");
        payment.add("Tiền mặt (COD)");
        payment.add("Thanh toán qua ngân hàng");
        payment.add("Thanh toán qua ví điện tử");

        adapter = new ArrayAdapter<>(CartActivity.this, android.R.layout.simple_list_item_1, payment);

        binding.spPayment.setAdapter(adapter);
    }
}