package com.group8.rakkiibookstoreapp;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group8.rakkiibookstoreapp.databinding.ActivityDashboardBinding;
import com.group8.rakkiibookstoreapp.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity {

    private ActivityEditProfileBinding binding;
    String nameUser, emailUser, usernameUser, passwordUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference("users");

        showData();

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isEmailChanged() || isPasswordChanged()) {
                    Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);

                    intent.putExtra("username", usernameUser);
                    intent.putExtra("name", nameUser);
                    intent.putExtra("email", emailUser);
                    intent.putExtra("password", passwordUser);

                    startActivity(intent);

                } else {
                    Toast.makeText(EditProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isNameChanged(){
        if (!nameUser.equals(binding.edtName.getText().toString())){
            reference.child(usernameUser).child("name").setValue(binding.edtName.getText().toString());
            nameUser = binding.edtName.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isEmailChanged(){
        if (!emailUser.equals(binding.edtEmail.getText().toString())){
            reference.child(usernameUser).child("email").setValue(binding.edtEmail.getText().toString());
            emailUser = binding.edtEmail.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isPasswordChanged(){
        if (!passwordUser.equals(binding.edtPassword.getText().toString())){
            reference.child(usernameUser).child("password").setValue(binding.edtPassword.getText().toString());
            passwordUser = binding.edtPassword.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public void showData(){
        Intent intent = getIntent();

        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");

        binding.edtName.setText(nameUser);
        binding.edtEmail.setText(emailUser);
        binding.edtUsername.setText(usernameUser);
        binding.edtPassword.setText(passwordUser);
    }
}