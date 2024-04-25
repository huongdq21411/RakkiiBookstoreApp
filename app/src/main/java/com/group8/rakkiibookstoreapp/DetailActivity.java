package com.group8.rakkiibookstoreapp;

import android.content.Intent;
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
import java.text.NumberFormat;
import java.util.Locale;

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
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
            DecimalFormat decimalFormat = (DecimalFormat)nf;
            String formattedPrice = decimalFormat.format(((BookList) object).getPrice());
            binding.txtCategory.setText(((BookList) object).getCategory());
        binding.txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy mã thể loại từ TextView
                String categoryText = binding.txtCategory.getText().toString();
                String categoryCode = getCategoryCodeForCategoryText(categoryText);

                // Tạo intent để chuyển đến màn hình danh sách sản phẩm và truyền mã thể loại
                Intent intent = new Intent(DetailActivity.this, ProductListActivity.class);
                intent.putExtra("category", categoryCode);
                startActivity(intent);
            }
        });

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
    private String getCategoryCodeForCategoryText(String categoryText) {
        // Thực hiện logic để ánh xạ từ văn bản thể loại sang mã thể loại tương ứng

        if (categoryText.equals("Văn học")) {
            return "cat1";
        } else if (categoryText.equals("Kinh tế")) {
            return "cat2";
        } else if (categoryText.equals("Tâm lý - Kỹ Năng")) {
            return "cat3";
        } else if (categoryText.equals("Sách thiếu nhi")) {
            return "cat4";
        } else if (categoryText.equals("Giáo khoa - Tham khảo")) {
            return "cat5";
        } else {
            return "defaultCategoryCode"; // Trả về một giá trị mặc định nếu không có sự tương ứng
        }
    }
}
