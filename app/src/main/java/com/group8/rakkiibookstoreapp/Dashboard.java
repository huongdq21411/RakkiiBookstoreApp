package com.group8.rakkiibookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.group8.rakkiibookstoreapp.adapter.PopularProductAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityDashboardBinding;
import com.group8.rakkiibookstoreapp.model.PopularProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;
import androidx.core.splashscreen.SplashScreen;

public class Dashboard extends AppCompatActivity {

    ActivityDashboardBinding binding;
    boolean isReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                if (isReady){
                    content.getViewTreeObserver().removeOnDrawListener(this);
                }
                dismissSplashScreen();
            }

        });
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        initRecyclerView();
        addEvents();
        bottomNavigation_cart();
        bottomNavigation_profile();
    }

    private void bottomNavigation_cart() {
        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, CartActivity.class));
            }
        });
    }

    private void bottomNavigation_profile() {
        binding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, ProfileActivity.class));
            }
        });
    }

    private void statusBarColor() {
        Window window = Dashboard.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(Dashboard.this, R.color.purple_Dark));
    }

    private void initRecyclerView() {
        ArrayList<PopularProduct> items = new ArrayList<>();
        items.add(new PopularProduct("Cây cam ngọt của tôi - Tặng kèm bookmark","item_1",7,4,108000,
                "\"“Vị chua chát của cái nghèo hòa trộn với vị ngọt ngào khi khám phá ra những điều khiến cuộc đời này đáng số một tác phẩm kinh điển của Brazil.” - Booklist\n" +
                        "Hãy làm quen với Zezé, cậu bé tinh nghịch siêu hạng đồng thời cũng đáng yêu bậc nhất, với ước mơ lớn lên trở thành nhà thơ cổ thắt nơ bướm. Chẳng phải ai cũng công nhận khoản “đáng yêu” kia đâu nhé. Bởi vì, ở cái xóm ngoại ô nghèo ấy, nỗi khắc khổ bủa vây đã che mờ mắt người ta trước trái tim thiện lương cùng trí tưởng tượng tuyệt vời của cậu bé con năm tuổi.\""));
        items.add(new PopularProduct("Mãi mãi là bao xa - Tái bản 2019","item_2",3,5,94000,"\"Em là cây hoa loa kèn hoang dã mãi mãi chỉ vì chính mình mà nở hoa, rời khỏi đất mẹ là cái giá phải trả khi yêu anh.\"\"\n" +
                "Bạch Lăng Lăng, nữ sinh khoa Điện khí, trẻ trung, xinh đẹp và rất tự hào khi quen được một người bạn lý tưởng qua mạng, chàng du học sinh của một trường nổi tiếng của Mỹ, người mang biệt danh “nhà khoa học”: Mãi Mãi Là Bao Xa. Qua những cuộc chuyện trò trên QQ, Lăng Lăng đã gắn bó với chàng trai đó lúc nào cô cũng không hay, cảm xúc lớn dần, sự chia sẻ lớn dần và đến một ngày cô phát hiện ra mình đã yêu người con trai “tài giỏi” và không một chút khuyết điểm ấy.\""));
        items.add(new PopularProduct("Thương tiến tửu - Tập 1","item_3",6,3,127000,"“Vận mệnh đã muốn ta suốt kiếp chôn chân tại chốn này, nhưng số mệnh ấy nào phải con đường ta lựa chọn. Cát vàng chôn vùi huynh đệ ta, ta không muốn tiếp tục thần phục số mệnh hư vô nữa. Thánh chỉ không cứu được sĩ tốt của ta, triều đình không nuôi nổi chiến mã của ta, ta không muốn liều mạng vì những thứ đó nữa. Ta muốn băng qua núi xanh kia, ta muốn đánh một trận, vì chính mình.”"));

        binding.rvPopular.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));
        binding.rvPopular.setAdapter(new PopularProductAdapter(items));
    }

    private void addEvents() {
        binding.imvCat1.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "cat1");
            startActivity(intent);
        });
        binding.imvCat2.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "cat2");
            startActivity(intent);
        });
        binding.imvCat3.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "cat3");
            startActivity(intent);
        });
        binding.imvCat4.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "cat4");
            startActivity(intent);
        });
        binding.imvCat5.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "cat5");
            startActivity(intent);
        });
        binding.imvCat6.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "nocat");
            startActivity(intent);
        });
        binding.textView3.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "nocat");
            startActivity(intent);
        });
    }
    private void dismissSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isReady = true;
            }
        }, 3000);
    }
}