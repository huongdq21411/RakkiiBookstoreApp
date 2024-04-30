package com.group8.rakkiibookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group8.rakkiibookstoreapp.databinding.ActivityBlogBinding;
import com.group8.rakkiibookstoreapp.databinding.ActivityBlogDetailBinding;
import com.group8.rakkiibookstoreapp.model.Blog;

import java.util.ArrayList;

public class BlogDetailActivity extends AppCompatActivity {

    ActivityBlogDetailBinding binding;
    private ArrayList<Blog> blogs;
    private TextView txtBlogContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityBlogDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        blogs = new ArrayList<>();

        blogs.add(new Blog(R.drawable.blog1, 1, "Vì sao nên đọc sách?", "Khám phá sâu hơn vào giá trị và lợi ích của việc đọc sách, blog sẽ mang lại sự thúc đẩy để mọi người dành thời gian và nỗ lực cho việc đọc sách.", 50.0, "29/4/2024"));
        blogs.add(new Blog(R.drawable.blog2, 2, "Tìm hiểu và chia sẻ những quyển sách đáng đọc", "Cung cấp các đánh giá chi tiết và nhận xét về các quyển sách nổi tiếng, giúp người đọc tìm hiểu và chọn lựa những tác phẩm đáng đọc trong đa dạng thể loại văn học.", 20.0, "29/4/2024"));
        blogs.add(new Blog(R.drawable.blog3, 3, "Khám phá thế giới sách qua công nghệ số", "Khám phá sách điện tử, ứng dụng đọc sách và những tài nguyên trực tuyến khác để mở rộng kiến thức và khám phá thế giới văn học thông qua công nghệ.", 25.0, "29/4/2024"));
        blogs.add(new Blog(R.drawable.blog4, 4, "Hướng dẫn đọc sách hiệu quả", "Cung cấp một loạt các gợi ý và phương pháp đọc sách hiệu quả, nhấn mạnh việc áp dụng tri thức từ sách vào cuộc sống hàng ngày.", 30.0, "29/4/2024"));
        blogs.add(new Blog(R.drawable.blog5, 5, "Khám phá và thúc đẩy sự sáng tạo qua việc đọc sách", "Khám phá mối liên hệ giữa sách và sự sáng tạo, cung cấp cảm hứng và phương pháp để khuyến khích sự sáng tạo thông qua việc đọc sách và khám phá văn học.", 10.0, "29/4/2024"));
        blogs.add(new Blog(R.drawable.blog6, 6, "Hành trình khám phá thế giới qua trang sách", "Khám phá thế giới qua sách, chia sẻ những trải nghiệm du lịch, hành trình và những câu chuyện thú vị liên quan đến sách và đọc.", 12.0, "29/4/2024"));
        blogs.add(new Blog(R.drawable.blog7, 7, "Học hỏi từ những cuốn sách của những người thành công", "Khám phá những cuốn sách được viết bởi những người thành công và mang đến những bài học, nguồn cảm hứng và phương pháp để áp dụng vào cuộc sống và đạt được thành công.", 18.0, "29/4/2024"));
        blogs.add(new Blog(R.drawable.blog8, 8, "Hành trình khám phá văn hóa và tri thức", "Khám phá các thể loại sách khác nhau từ khắp nơi trên thế giới và mang đến cái nhìn sâu sắc về văn hóa, tri thức và những cảm nhận cá nhân về từng tác phẩm.", 6.0, "29/4/2024"));


        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            // Lấy giá trị id của Blog được chọn từ Intent
            int selectedBlogId = intent.getIntExtra("blog", -1);

            // Tìm bài viết được chọn trong danh sách blogs
            Blog selectedBlog = findBlogById(selectedBlogId);

            // Hiển thị nội dung bài viết trong TextView
            if (selectedBlog != null) {
                binding.txtBlogTitle.setText(selectedBlog.getBlogTitle());
                binding.txtBlogTime.setText(selectedBlog.getBlogTime());
                binding.txtView.setText(String.valueOf(selectedBlog.getBlogView() + " Lượt xem"));
                binding.txtBlogContent.setText(selectedBlog.getBlogContent());
                binding.imvBlog.setImageResource(selectedBlog.getBlogPhoto());
            }
        }

        txtBlogContent = findViewById(R.id.txtBlogContent);

        Intent intentblog = getIntent();
        if (intentblog != null) {
            int selectedBlogId = intentblog.getIntExtra("blog", -1);
            String blogContent = getBlogContentById(selectedBlogId);
            if (blogContent != null) {
                txtBlogContent.setText(blogContent);
            }
        }
    }

    private String getBlogContentById(int blogId) {
        switch (blogId) {
            case 1:
                return getString(R.string.blogcontent1);
            case 2:
                return getString(R.string.blogcontent2);
            case 3:
                return getString(R.string.blogcontent3);
            case 4:
                return getString(R.string.blogcontent4);
            case 5:
                return getString(R.string.blogcontent5);
            case 6:
                return getString(R.string.blogcontent6);
            case 7:
                return getString(R.string.blogcontent7);
            case 8:
                return getString(R.string.blogcontent8);
            default:
                return null;
        }
    }



    private Blog findBlogById(int blogId) {
        for (Blog blog : blogs) {
            if (blog.getBlogId() == blogId) {
                return blog;
            }
        }
        return null;
    }


}