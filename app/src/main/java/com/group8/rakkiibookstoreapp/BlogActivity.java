package com.group8.rakkiibookstoreapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group8.rakkiibookstoreapp.adapter.BlogAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityBlogBinding;
import com.group8.rakkiibookstoreapp.model.Blog;

import java.util.ArrayList;

public class BlogActivity extends AppCompatActivity {

    ActivityBlogBinding binding;
    BlogAdapter blogAdapter;
    ArrayList<Blog> blogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBlogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.imvBack.setOnClickListener(v -> finish());

        initData();
        loadData();

    }

    private void initData() {
        blogs = new ArrayList<>();
        blogs.add(new Blog(R.drawable.blog1, "Vì sao nên đọc sách? ", "Khám phá sâu hơn vào giá trị và lợi ích của việc đọc sách, blog sẽ mang lại sự thúc đẩy để mọi người dành thời gian và nỗ lực cho việc đọc sách.", 50.0));
        blogs.add(new Blog(R.drawable.blog2, "Tìm hiểu và chia sẻ những quyển sách đáng đọc", "Cung cấp các đánh giá chi tiết và nhận xét về các quyển sách nổi tiếng, giúp người đọc tìm hiểu và chọn lựa những tác phẩm đáng đọc trong đa dạng thể loại văn học.", 20.0));
        blogs.add(new Blog(R.drawable.blog3, "Khám phá thế giới sách qua công nghệ số", "Khám phá sách điện tử, ứng dụng đọc sách và những tài nguyên trực tuyến khác để mở rộng kiến thức và khám phá thế giới văn học thông qua công nghệ.", 25.0));
        blogs.add(new Blog(R.drawable.blog4, "Hướng dẫn đọc sách hiệu quả", "Cung cấp một loạt các gợi ý và phương pháp đọc sách hiệu quả, nhấn mạnh việc áp dụng tri thức từ sách vào cuộc sống hàng ngày.", 30.0));
        blogs.add(new Blog(R.drawable.blog5, "Khám phá và thúc đẩy sự sáng tạo qua việc đọc sách", "Khám phá mối liên hệ giữa sách và sự sáng tạo, cung cấp cảm hứng và phương pháp để khuyến khích sự sáng tạo thông qua việc đọc sách và khám phá văn học.", 10.0));
        blogs.add(new Blog(R.drawable.blog6, "Hành trình khám phá thế giới qua trang sách", "Khám phá thế giới qua sách, chia sẻ những trải nghiệm du lịch, hành trình và những câu chuyện thú vị liên quan đến sách và đọc.", 12.0));
        blogs.add(new Blog(R.drawable.blog7, "Học hỏi từ những cuốn sách của những người thành công", "Khám phá những cuốn sách được viết bởi những người thành công và mang đến những bài học, nguồn cảm hứng và phương pháp để áp dụng vào cuộc sống và đạt được thành công.", 18.0));
        blogs.add(new Blog(R.drawable.blog8, "Hành trình khám phá văn hóa và tri thức", "Khám phá các thể loại sách khác nhau từ khắp nơi trên thế giới và mang đến cái nhìn sâu sắc về văn hóa, tri thức và những cảm nhận cá nhân về từng tác phẩm.", 6.0));
    }

    private void loadData() {
        initData();
        blogAdapter = new BlogAdapter(BlogActivity.this, R.layout.item_blog, blogs);
        binding.lvBlog.setAdapter(blogAdapter);
    }

}