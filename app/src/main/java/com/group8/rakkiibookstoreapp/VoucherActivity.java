package com.group8.rakkiibookstoreapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group8.rakkiibookstoreapp.adapter.VoucherAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityVoucherBinding;
import com.group8.rakkiibookstoreapp.model.Voucher;

import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {

    ActivityVoucherBinding binding;
    VoucherAdapter voucherAdapter;
    ArrayList<Voucher> vouchers;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        statusBarColor();
        loadData();

    }

    private void initData() {
        vouchers = new ArrayList<>();

        vouchers.add(new Voucher(R.drawable.baseline_discount_24, 1, "Giảm 10% tối đa 50k", "Tiêu dùng ngay", "Đơn tối thiểu 0đ", "HSD: 30/05/2024"));
        vouchers.add(new Voucher(R.drawable.baseline_discount_24, 2, "Giảm 10% tối đa 100k", "Tiêu dùng ngay", "Đơn tối thiểu 50k", "HSD: 30/05/2024"));
        vouchers.add(new Voucher(R.drawable.baseline_discount_24, 3, "Giảm tối đa 20k", "Tiêu dùng ngay", "Đơn tối thiểu 0đ", "HSD: 30/05/2024"));
        vouchers.add(new Voucher(R.drawable.baseline_discount_24, 4, "Giảm tối đa 30k", "Tiêu dùng ngay", "Đơn tối thiểu 0đ", "HSD: 30/05/2024"));
        vouchers.add(new Voucher(R.drawable.baseline_airport_shuttle_24, 5, "Miễn phí vận chuyển", "Mã vận chuyển", "Đơn tối thiểu 50k", "HSD: 30/05/2024"));
        vouchers.add(new Voucher(R.drawable.baseline_airport_shuttle_24, 6, "Giảm tối đa 20k", "Mã vận chuyển", "Đơn tối thiểu 0đ", "HSD: 30/05/2024"));
        vouchers.add(new Voucher(R.drawable.baseline_airport_shuttle_24, 7, "Giảm tối đa 30k", "Mã vận chuyển", "Đơn tối thiểu 0đ", "HSD: 30/05/2024"));
        vouchers.add(new Voucher(R.drawable.baseline_airport_shuttle_24, 8, "Giảm tối đa 50k", "Mã vận chuyển", "Đơn tối thiểu 0đ", "HSD: 30/05/2024"));
    }

    private void loadData() {
        initData();
        voucherAdapter = new VoucherAdapter(VoucherActivity.this, R.layout.item_voucher, vouchers);
        binding.lvVoucher.setAdapter(voucherAdapter);
    }

    private void statusBarColor() {
        Window window = VoucherActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(VoucherActivity.this, R.color.purple_Dark));
    }
}