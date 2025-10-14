package com.example.inmobiliaria_2025;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity {

    private EditText etMail, etClave;
    private Button btIngresar;
    private LoginActivityViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = findViewById(R.id.etMail);
        etClave = findViewById(R.id.etClave);
        btIngresar = findViewById(R.id.btIngresar);

        loginViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        loginViewModel.getMensaje().observe(this, mensaje -> {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        });

        btIngresar.setOnClickListener(v -> {
            String email = etMail.getText().toString().trim();
            String clave = etClave.getText().toString().trim();

            if (email.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completá email y contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.login(email, clave, this);
        });
    }
}
