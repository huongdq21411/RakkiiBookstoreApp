package com.group8.rakkiibookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group8.rakkiibookstoreapp.databinding.ActivityEditProfileBinding;
import com.group8.rakkiibookstoreapp.databinding.ActivitySignupBinding;
import com.group8.rakkiibookstoreapp.helper.Login_Signup;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = binding.signupName.getText().toString();
                String email = binding.signupEmail.getText().toString();
                String username = binding.signupUsername.getText().toString();
                String password = binding.signupPassword.getText().toString();

                Login_Signup login_signup = new Login_Signup(name, email, username, password);
                reference.child(username).setValue(login_signup);

                Toast.makeText(SignupActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}