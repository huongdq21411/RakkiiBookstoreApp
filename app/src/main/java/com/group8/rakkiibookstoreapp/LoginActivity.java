package com.group8.rakkiibookstoreapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group8.rakkiibookstoreapp.databinding.ActivityEditProfileBinding;
import com.group8.rakkiibookstoreapp.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth auth;
    String nameUser, emailUser, usernameUser, passwordUser;
    DatabaseReference reference;
    TextView forgotPassword;
    boolean isReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                if (isReady){
                    // Use a Handler to defer the removal of the listener
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            ViewTreeObserver.OnDrawListener onDrawListener;
                            onDrawListener = null;
                            content.getViewTreeObserver().removeOnDrawListener(null);
                        }
                    });
                }
                dismissSplashScreen();
            }

        });
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(!validateUsername() | !validatePassword())) {
                    checkUser();
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

//        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                View dialogView = getLayoutInflater().inflate(R.layout.forgot_dialog_box, null);
//                EditText emailBox = dialogView.findViewById(R.id.edtEmailBox);
//
//                builder.setView(dialogView);
//                AlertDialog dialog = builder.create();
//
//                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String userEmail = emailBox.getText().toString();
//
//                        if (TextUtils.isEmpty(userEmail) ||!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
//                            Toast.makeText(LoginActivity.this, "Điền địa chỉ Email của bạn", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        auth = FirebaseAuth.getInstance();
//                        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()){
//                                    Toast.makeText(LoginActivity.this, "Kiểm tra hộp thư của " +
//                                            "bạn!", Toast.LENGTH_SHORT).show();
//                                    dialog.dismiss();
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "Không thể gửi tới địa chỉ" +
//                                            " Email này!", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                });
//                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                if (dialog.getWindow() != null) {
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//                }
//                dialog.show();
//            }
//        });
    }

    public Boolean validateUsername() {
        String val = binding.loginUsername.getText().toString();
        if (val.isEmpty()) {
            binding.loginUsername.setError("Tên đăng nhập không được để trống");
            return false;
        } else {
            binding.loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = binding.loginPassword.getText().toString();
        if (val.isEmpty()) {
            binding.loginPassword.setError("Mật khẩu không được để trống!");
            return false;
        } else {
            binding.loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userUsername = binding.loginUsername.getText().toString().trim();
        String userPassword = binding.loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    binding.loginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    assert passwordFromDB != null;
                    if (passwordFromDB.equals(userPassword)) {
                        binding.loginUsername.setError(null);

                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                        Intent intent = new Intent(LoginActivity.this, Dashboard.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);

                    } else {
                        binding.loginPassword.setError("Tài khoản này không hợp lệ");
                        binding.loginPassword.requestFocus();
                    }
                } else {
                    binding.loginUsername.setError("Tài khoản này không tồn tại");
                    binding.loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
}