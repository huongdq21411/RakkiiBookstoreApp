package com.group8.rakkiibookstoreapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.group8.rakkiibookstoreapp.adapter.BookListAdapter;
import com.group8.rakkiibookstoreapp.adapter.PopularProductAdapter;
import com.group8.rakkiibookstoreapp.databinding.ActivityDashboardBinding;
import com.group8.rakkiibookstoreapp.model.BookList;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    ActivityDashboardBinding binding;
    String nameUser, emailUser, usernameUser, passwordUser;
    boolean isReady = false;
    private ArrayList<BookList> items;
    boolean nightmode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");

        statusBarColor();
        initRecyclerView();
        addEvents();
        bottomNavigation_fav();
        bottomNavigation_cart();
        bottomNavigation_profile();
        bottomNavigation_blog();

        binding.imvQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRScanner();
            }
        });
        showHello();

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightmode = sharedPreferences.getBoolean("nightmode", false);
        binding.swthemeSwitch.setChecked(nightmode);
        if (nightmode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        binding.swthemeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nightmode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                editor = sharedPreferences.edit();
                editor.putBoolean("nightmode", !nightmode);
                editor.apply();
                nightmode = !nightmode;
            }
        });
    }



    private void showHello() {
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("name");
        binding.txtUserName.setText(nameUser);
    }

    private void bottomNavigation_fav() {
        binding.btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, FavoriteListActivity.class));
            }
        });
    }

    private void bottomNavigation_blog() {
        binding.btnBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, BlogActivity.class));
            }
        });
    }

    private void bottomNavigation_cart() {
        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, CartListActivity.class));
            }
        });
    }

    private void bottomNavigation_profile() {
        binding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, ProfileActivity.class);
                intent.putExtra("name", nameUser);
                intent.putExtra("email", emailUser);
                intent.putExtra("username", usernameUser);
                intent.putExtra("password", passwordUser);
                startActivity(intent);
            }
        });
        String userUsername = binding.txtUserName.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);

                    Intent intent = new Intent(Dashboard.this, ProfileActivity.class);

                    intent.putExtra("name", nameFromDB);

                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    private void statusBarColor() {
        Window window = Dashboard.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(Dashboard.this, R.color.purple_Dark));
    }

    private void initRecyclerView() {

        ArrayList<BookList> items = new ArrayList<>();
        items.add(new BookList(1, "Văn học", "José Mauro de Vasconcelos","NXB Nhà Văn","Cây cam ngọt của tôi", "item_1", 7, 4, 108000, "“Vị chua chát của cái nghèo hòa trộn với vị ngọt ngào khi khám phá ra những điều khiến cuộc đời này đáng số một tác phẩm kinh điển của Brazil.” - Booklist\nHãy làm quen với Zezé, cậu bé tinh nghịch siêu hạng đồng thời cũng đáng yêu bậc nhất, với ước mơ lớn lên trở thành nhà thơ cổ thắt nơ bướm. Chẳng phải ai cũng công nhận khoản “đáng yêu” kia đâu nhé. Bởi vì, ở cái xóm ngoại ô nghèo ấy, nỗi khắc khổ bủa vây đã che mờ mắt người ta trước trái tim thiện lương cùng trí tưởng tượng tuyệt vời của cậu bé con năm tuổi."));
        items.add(new BookList(2, "Văn học", "Diệp Lạc Vô Tâm","NXB Thanh Niên","Mãi mãi là bao xa - Tái bản 2019", "item_2", 3, 5, 94000, "Em là cây hoa loa kèn hoang dã mãi mãi chỉ vì chính mình mà nở hoa, rời khỏi đất mẹ là cái giá phải trả khi yêu anh.\"\"\nBạch Lăng Lăng, nữ sinh khoa Điện khí, trẻ trung, xinh đẹp và rất tự hào khi quen được một người bạn lý tưởng qua mạng, chàng du học sinh của một trường nổi tiếng của Mỹ, người mang biệt danh “nhà khoa học”: Mãi Mãi Là Bao Xa. Qua những cuộc chuyện trò trên QQ, Lăng Lăng đã gắn bó với chàng trai đó lúc nào cô cũng không hay, cảm xúc lớn dần, sự chia sẻ lớn dần và đến một ngày cô phát hiện ra mình đã yêu người con trai “tài giỏi” và không một chút khuyết điểm ấy."));
        items.add(new BookList(3, "Văn học", "Đường Tửu Khanh",	"NXB Hà Nội","Thương tiến tửu - Tập 1", "item_3", 6, 3, 127000, "Vận mệnh đã muốn ta suốt kiếp chôn chân tại chốn này, nhưng số mệnh ấy nào phải con đường ta lựa chọn. Cát vàng chôn vùi huynh đệ ta, ta không muốn tiếp tục thần phục số mệnh hư vô nữa. Thánh chỉ không cứu được sĩ tốt của ta, triều đình không nuôi nổi chiến mã của ta, ta không muốn liều mạng vì những thứ đó nữa. Ta muốn băng qua núi xanh kia, ta muốn đánh một trận, vì chính mình."));

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
        binding.Banner.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "nocat");
            startActivity(intent);
        });
        binding.textView3.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
            intent.putExtra("category", "nocat");
            startActivity(intent);
        });
        binding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, ProductListActivity.class);
                intent.putExtra("category", "cat1");
                startActivity(intent);
            }
        });
        binding.imvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, SearchActivity.class);
                startActivity(intent);
            }
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

    // Hàm để bắt đầu quét mã QR
    private void startQRScanner() {
        // Tạo danh sách các lựa chọn cho người dùng chọn giữa quét trực tiếp và tải ảnh từ máy
        CharSequence[] options = {"Quét trực tiếp", "Tải ảnh từ máy"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        builder.setTitle("Chọn phương thức quét");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Quét trực tiếp")) {
                    // Quét trực tiếp
                    scanQRCode();
                } else if (options[item].equals("Tải ảnh từ máy")) {
                    // Tải ảnh từ máy để quét
                    selectImageFromGallery();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_background);

    }

    private void scanQRCode() {
        ScanOptions options = new ScanOptions();
        options.setBeepEnabled(true);
        options.setPrompt("Vui lòng đưa mã QR vào vùng quét mã!");
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    // Xử lý kết quả quét mã QR
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result != null) {
            if (result.getContents() == null) {
                // Nếu người dùng hủy quét
                Toast.makeText(this, "Bạn đã hủy quét.", Toast.LENGTH_SHORT).show();
            } else {
                // Kiểm tra xem kết quả có phải là một đường dẫn URL hay không
                String scannedResult = result.getContents();
                if (isURL(scannedResult)) {
                    // Nếu là đường dẫn URL, mở trình duyệt web và hiển thị trang web
                    openWebPage(scannedResult);
                } else {
                    // Nếu không phải là đường dẫn URL, thông báo không phải
                    Toast.makeText(this, "Kết quả không phải là một đường dẫn URL: " + scannedResult, Toast.LENGTH_SHORT).show();
                }
            }
        }
    });



    private void selectImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            // Lấy URI của ảnh từ intent
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // Thực hiện quét ảnh từ máy
                scanImage(selectedImageUri);
            }
        }
    }

    private void scanImage(Uri imageUri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            // Sử dụng thư viện ZXing để quét ảnh
            BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext())
                    .setBarcodeFormats(Barcode.QR_CODE)
                    .build();

            if (!detector.isOperational()) {
                Toast.makeText(this, "Không thể quét ảnh. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                return;
            }

            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<Barcode> barcodes = detector.detect(frame);

            if (barcodes.size() == 0) {
                Toast.makeText(this, "Không tìm thấy mã QR trong ảnh.", Toast.LENGTH_SHORT).show();
            } else {
                Barcode scannedResult = barcodes.valueAt(0);
                handleScanResult(scannedResult.displayValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Không thể quét ảnh. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
        }
    }


    // Xử lý kết quả sau khi quét ảnh hoặc QR code trực tiếp
    private void handleScanResult(String scannedResult) {
        if (isURL(scannedResult)) {
            // Nếu là đường dẫn URL, mở trình duyệt web và hiển thị trang web
            openWebPage(scannedResult);
        } else {
            // Nếu không phải là đường dẫn URL, thông báo không phải
            Toast.makeText(this, "Kết quả không phải là một đường dẫn URL: " + scannedResult, Toast.LENGTH_SHORT).show();
        }
    }



    // Hàm kiểm tra xem chuỗi có phải là một đường dẫn URL hay không
    private boolean isURL(String text) {
        return text != null && (text.startsWith("http://") || text.startsWith("https://"));
    }

    // Hàm mở trình duyệt web và hiển thị trang web tương ứng
    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    @Override
    public void recreate() {
        finish();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }
}